package tests;

import api.MemberPointsAPI;
import com.opencsv.exceptions.CsvException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MemberPointsTests extends BaseTest{

    private MemberPointsAPI memberPointsAPI = new MemberPointsAPI();

    public MemberPointsTests() {
        super(new MemberPointsAPI(), MemberPointsAPI.class.getSimpleName());
    }

    @Test
    public void validateNameAccordingToID(){
        log.info("Validate that the name of the Survey matches with the ID");
        Assert.assertTrue(memberPointsAPI.validateNameAccordingToID(), "The 'name' field does not contain the 'id' field.");
    }

    @Test
    public void validatePoints() throws CsvException {
        log.info("Validate that point field is correct according the Status of the Survey");
        Assert.assertTrue(memberPointsAPI.validatePoints(), "The points according to the status are not correct.");
    }
}

