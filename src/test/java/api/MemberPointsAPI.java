package api;

import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
import org.testng.SkipException;
import utils.ApiInterface;
import utils.TestUtil;

import java.util.LinkedHashMap;
import java.util.Map;

import static utils.Constants.MEMBER_ID;
import static utils.Constants.MEMBER_POINTS_API;

public class MemberPointsAPI extends BaseMethods implements ApiInterface {

    @Override
    public int getStatusCode() {
        params.put("memberId", MEMBER_ID);
        return validateStatusCodeUsingParams(MEMBER_POINTS_API, params);
    }

    @Override
    public ValidatableResponse validateSchema() {
        return validateSchemaUsingParams(MEMBER_POINTS_API,params);
    }

    public boolean validateNameAccordingToID(){
        params.put("memberId", MEMBER_ID);
        JsonPath jsonPath = getResponseUsingParams(MEMBER_POINTS_API,params).jsonPath();
        int objectSize = jsonPath.getList("$").size();

        for(int i=0; i<objectSize; i++){
            int id = jsonPath.getInt("[" + i + "].survey.id");
            String name = jsonPath.getString("[" + i + "].survey.name");
            System.out.println("ID: "+id+" - Name: "+name);
            if(!name.contains(String.valueOf(id))) return false;
        }
        return true;
    }

    public boolean validatePoints(){
        LinkedHashMap<String, String> map = TestUtil.getParticipationStatus(String.valueOf(MEMBER_ID));

        for (Map.Entry<String, String> entry : map.entrySet()) {
            String surveyId = entry.getKey();
            String status = entry.getValue();

            switch (status) {
                case "1", "2" -> {
                    System.out.println("*Not Asked and Rejected Participation Status*");
                    if (getPoint(surveyId) != 0) return false;
                }
                case "3" -> {
                    System.out.println("*Filtered Participation Status*");
                    if (getFilteredPoints(surveyId) != getPoint(surveyId)) return false;
                }
                case "4" -> {
                    System.out.println("*Completed Participation Status*");
                    if (getCompletionPoints(surveyId) != getPoint(surveyId)) return false;
                }
                default -> throw new SkipException("Skipping Test.");
            }
        }
        return true;
    }

    public int getFilteredPoints(String surveyId){
        JsonPath jsonPath = getResponseUsingParams(MEMBER_POINTS_API,params).jsonPath();
        return jsonPath.getInt(String.format("find { it.survey.id == %s }.survey.filteredPoints", surveyId));
    }

    public int getCompletionPoints(String surveyId){
        JsonPath jsonPath = getResponseUsingParams(MEMBER_POINTS_API,params).jsonPath();
        return jsonPath.getInt(String.format("find { it.survey.id == %s }.survey.completionPoints", surveyId));
    }

    public int getPoint(String surveyId){
        JsonPath jsonPath = getResponseUsingParams(MEMBER_POINTS_API,params).jsonPath();
        int points = jsonPath.getInt(String.format("find { it.survey.id == %s }.point", surveyId));
        System.out.println("Points received: "+points+"\n");
        return points;
    }
}
