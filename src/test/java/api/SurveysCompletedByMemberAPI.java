package api;

import com.opencsv.exceptions.CsvException;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.SkipException;
import utils.ApiInterface;
import utils.TestUtil;

import static utils.Constants.MEMBER_ID;
import static utils.Constants.SURVEYS_COMPLETED_BY_MEMBER_API;

public class SurveysCompletedByMemberAPI extends BaseMethods implements ApiInterface {
    @Override
    public int getStatusCode() {
        params.put("memberId", MEMBER_ID);

        return validateStatusCodeUsingParams(SURVEYS_COMPLETED_BY_MEMBER_API, params);
    }

    @Override
    public ValidatableResponse validateSchema() {

        return validateSchemaUsingParams(SURVEYS_COMPLETED_BY_MEMBER_API,params);
    }

    public Response validateResponseAccordingUsers(){
        //If user exists, the body shouldn't be empty. If it doesn't exist, the body should be empty.
        params.put("memberId", MEMBER_ID);

        return getResponseUsingParams(SURVEYS_COMPLETED_BY_MEMBER_API,params);
    }

    public boolean validateNameAccordingToID() throws CsvException {
        if(TestUtil.checkIfMemberOrSurveyExists(TestUtil.members)){
            params.put("memberId", MEMBER_ID);
            JsonPath jsonPath = getResponseUsingParams(SURVEYS_COMPLETED_BY_MEMBER_API,params).jsonPath();
            int objectSize = jsonPath.getList("$").size();
            for(int i=0; i<objectSize; i++){
                int id = jsonPath.getInt("[" + i + "].id");
                String name = jsonPath.getString("[" + i + "].name");
                System.out.println("ID: "+id+" - Name: "+name);
                if(!name.contains(String.valueOf(id))) return false;
            }
            return true;
        }else{
            System.out.println("TEST SKIPPED because the user not exists");
            throw new SkipException("Skipping test: validateResponseForNonExistentUsers");
        }

    }
}
