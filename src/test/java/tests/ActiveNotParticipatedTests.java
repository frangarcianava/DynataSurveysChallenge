package tests;

import api.ActiveNotParticipatedAPI;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ActiveNotParticipatedTests extends BaseTest{

    private ActiveNotParticipatedAPI activeNotParticipatedAPI = new ActiveNotParticipatedAPI();

    public ActiveNotParticipatedTests() {
        super(new ActiveNotParticipatedAPI(),ActiveNotParticipatedAPI.class.getSimpleName());
    }

    @Test
    public void validateActiveField(){
        log.info("Validate the members are Active");
        for(Boolean active : activeNotParticipatedAPI.validateActiveField()){
            Assert.assertTrue(active);
        }
    }
}
