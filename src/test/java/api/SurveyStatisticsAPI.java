package api;

import io.restassured.response.ValidatableResponse;
import utils.ApiInterface;
import utils.RestAssuredUtil;

import static utils.Constants.*;

public class SurveyStatisticsAPI extends BaseMethods implements ApiInterface {
    @Override
    public int getStatusCode() {
        return validateStatusCode(SURVEY_STATISTICS_API);
    }

    @Override
    public ValidatableResponse validateSchema() {
        return validateSchema(SURVEY_STATISTICS_API);
    }

    public int getMembersOfSurveySize(String status){
        params.put("surveyId", SURVEY_ID);
        params.put("status", status);
        int numberOfSurveys = RestAssuredUtil.getSize(getResponseUsingParams(GET_MEMBERS_OF_SURVEY_API, params));
        System.out.println("Number of " + status + " in Members Of Survey API: "+numberOfSurveys);

        return numberOfSurveys;
    }

    public int getNumberOfFiltered(){
        int numberOfFiltered = getResponse(SURVEY_STATISTICS_API)
                .path("find { it.surveyId ==" +SURVEY_ID+ "}.numberOfFiltered");
        System.out.println("Number of FILTERED in Survey Statistics API: "+numberOfFiltered);
        return numberOfFiltered;
    }

    public int getNumberOfRejected(){
        int numberOfRejected = getResponse(SURVEY_STATISTICS_API)
                .path("find { it.surveyId ==" +SURVEY_ID+ "}.numberOfRejected");
        System.out.println("Number of REJECTED in Survey Statistics API: "+numberOfRejected);

        return numberOfRejected;
    }
}
