package api;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import utils.ApiInterface;
import utils.TestUtil;

import static utils.Constants.*;

public class GetMembersOfSurveyAPI extends BaseMethods implements ApiInterface {

    @Override
    public int getStatusCode() {
        params.put("surveyId", SURVEY_ID);

        for(String status : TestUtil.statuses){
            params.put("status", status);
            statusCode = validateStatusCodeUsingParams(GET_MEMBERS_OF_SURVEY_API, params);
            System.out.println(status+ ": " +statusCode);
        }
        return validateStatusCodeUsingParams(GET_MEMBERS_OF_SURVEY_API, params);
    }

    @Override
    public ValidatableResponse validateSchema() {
        params.put("surveyId", SURVEY_ID);
        ValidatableResponse response = null;

        for(String status : TestUtil.statuses){
            params.put("status", status);
            response = validateSchemaUsingParams(GET_MEMBERS_OF_SURVEY_API, params);
            System.out.println(status+ ": \n" +response.extract().body().asPrettyString());
        }

        return response;
    }

    public Response validateResponseWithInvalidStatus(){
        params.put("surveyId", SURVEY_ID);
        params.put("status", INVALID_STATUS);
        return getResponseUsingParams(GET_MEMBERS_OF_SURVEY_API,params);
    }

    public Response validateResponseAccordingStatus(String api){
        params.put("surveyId", SURVEY_ID);
        params.put("status", api);
        return getResponseUsingParams(GET_MEMBERS_OF_SURVEY_API,params);
    }

}
