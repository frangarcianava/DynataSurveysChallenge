package api;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.SkipException;
import utils.ApiInterface;

import java.util.List;

import static utils.Constants.ACTIVE_NOT_PARTICIPATED_API;
import static utils.Constants.SURVEY_ID;

public class ActiveNotParticipatedAPI extends BaseMethods implements ApiInterface {

    @Override
    public int getStatusCode() {
        params.put("surveyId",SURVEY_ID);
        return validateStatusCodeUsingParams(ACTIVE_NOT_PARTICIPATED_API,params);
    }

    @Override
    public ValidatableResponse validateSchema() {
        return validateSchemaUsingParams(ACTIVE_NOT_PARTICIPATED_API,params);
    }

    public List<Boolean> validateActiveField(){
        if(getStatusCode() == 200){
            Response response = getResponseUsingParams(ACTIVE_NOT_PARTICIPATED_API,params);
            response.then().log().body();
            return response.jsonPath().getList("active");
        }else{
            System.out.println("TEST SKIPPED because the response was not OK (200)");
            throw new SkipException("Skipping Test.");
        }
    }
}
