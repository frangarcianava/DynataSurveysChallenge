package tests;

import api.SurveyStatisticsAPI;
import org.testng.Assert;
import org.testng.annotations.Test;

import static utils.Constants.STATUS_FILTERED;
import static utils.Constants.STATUS_REJECTED;

public class SurveyStatisticsTests extends BaseTest{

    private SurveyStatisticsAPI surveyStatisticsAPI = new SurveyStatisticsAPI();

    public SurveyStatisticsTests() {
        super(new SurveyStatisticsAPI(), SurveyStatisticsAPI.class.getSimpleName());
    }

    @Test
    public void validateNumberOfFiltered(){
        log.info("Validate that the number of filtered matches between 'Get members of Survey API' and 'Survey Statistics API'");
        Assert.assertEquals(surveyStatisticsAPI.getNumberOfFiltered(),surveyStatisticsAPI.getMembersOfSurveySize(STATUS_FILTERED), "The number of filtered should match.");
    }

    @Test
    public void ValidateNumberOfRejected(){
        log.info("Validate that the number of rejected matches between 'Get members of Survey API' and 'Survey Statistics API'");
        Assert.assertEquals(surveyStatisticsAPI.getNumberOfRejected(),surveyStatisticsAPI.getMembersOfSurveySize(STATUS_REJECTED), "The number of rejected should match.");
    }

    //I was going to apply the same with 'Completion points' but it seems that there's a bug there.
}
