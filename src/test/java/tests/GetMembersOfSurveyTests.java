package tests;

import api.GetMembersOfSurveyAPI;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static utils.Constants.*;

public class GetMembersOfSurveyTests extends BaseTest{

    private GetMembersOfSurveyAPI getMembersOfSurveyAPI = new GetMembersOfSurveyAPI();

    public GetMembersOfSurveyTests() {
        super(new GetMembersOfSurveyAPI(), GetMembersOfSurveyAPI.class.getSimpleName());
    }

    @Test
    public void validateResponseWithInvalidStatus(){
        log.info("Validate response when a non existent status is sent");
        getMembersOfSurveyAPI.validateResponseWithInvalidStatus()
                .then()
                .statusCode(400).log().status();
    }

    @Test
    public void validateResponseForNOT_ASKEDStatus(){
        log.info("Validate response when 'NOT_ASKED' status is sent");
        getMembersOfSurveyAPI.validateResponseAccordingStatus(STATUS_NOT_ASKED)
                .then()
                .body("isEmpty()", Matchers.is(true)).log().body();
    }

    @Test
    public void validateResponseForCOMPLETEDStatus(){
        log.info("Validate response when 'COMPLETED' status is sent");
        getMembersOfSurveyAPI.validateResponseAccordingStatus(STATUS_COMPLETED)
                .then()
                .body("isEmpty()", Matchers.is(false)).log().body();
    }

    @Test
    public void validateResponseForFILTEREDStatus(){
        log.info("Validate response when 'FILTERED' status is sent");
        getMembersOfSurveyAPI.validateResponseAccordingStatus(STATUS_FILTERED)
                .then()
                .body("isEmpty()", Matchers.is(false)).log().body();
    }

    @Test
    public void validateResponseForREJECTEDStatus(){
        log.info("Validate response when 'REJECTED' status is sent");
        getMembersOfSurveyAPI.validateResponseAccordingStatus(STATUS_REJECTED)
                .then()
                .body("isEmpty()", Matchers.is(false)).log().body();
    }
}
