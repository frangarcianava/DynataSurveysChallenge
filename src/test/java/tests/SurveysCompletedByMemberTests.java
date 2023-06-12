package tests;

import api.SurveysCompletedByMemberAPI;
import com.opencsv.exceptions.CsvException;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;
import utils.TestUtil;

import static utils.Constants.MEMBER_ID;

public class SurveysCompletedByMemberTests extends BaseTest{

    private SurveysCompletedByMemberAPI surveysCompletedByMemberAPI = new SurveysCompletedByMemberAPI();

    public SurveysCompletedByMemberTests() {
        super(new SurveysCompletedByMemberAPI(), SurveysCompletedByMemberAPI.class.getSimpleName());
    }

    @Test
    public void validateResponseForNonExistentUsers() throws CsvException {
        //If user doesn't exist, the body should be empty.
        if(!TestUtil.checkIfMemberOrSurveyExists(TestUtil.members)){
            log.info("Validate response for non existent users.");
            surveysCompletedByMemberAPI.validateResponseAccordingUsers()
                    .then()
                    .body("isEmpty()", Matchers.is(true)).log().body();
        }else{
            log.info("TEST SKIPPED because the user exists.");
            throw new SkipException("Skipping test: validateResponseForNonExistentUsers");
        }
    }

    @Test
    public void validateResponseForExistentUsers(){
        //If user exists, the body shouldn't be empty.
        System.out.println("member: "+MEMBER_ID);
        if(MEMBER_ID <= 300){
            log.info("Validate response for existent users.");
            surveysCompletedByMemberAPI.validateResponseAccordingUsers()
                    .then()
                    .body("isEmpty()", Matchers.is(false)).log().body();
        }else{
            log.info("TEST SKIPPED because the user not exists.");
            throw new SkipException("Skipping test: validateResponseForNonExistentUsers");
        }
    }

    @Test
    public void validateNameAccordingToID() throws CsvException {
        log.info("Validate that the name of the Survey matches with the ID.");
        Assert.assertTrue(surveysCompletedByMemberAPI.validateNameAccordingToID(), "The 'name' field does not contain the 'id' field.");
    }


}
