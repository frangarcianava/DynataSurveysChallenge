package tests;

import com.opencsv.exceptions.CsvException;
import io.restassured.http.ContentType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.ApiInterface;
import utils.TestUtil;

import java.io.File;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static utils.Constants.MEMBER_ID;
import static utils.Constants.SURVEY_ID;

public class BaseTest {
    protected ApiInterface api;
    protected String className;
    protected static Logger log = LogManager.getLogger();

    public BaseTest(ApiInterface api, String className) {
        this.api = api;
        this.className = className;
    }

    @BeforeClass
    public void setup() {
        utils.RestAssuredUtil.setBaseURI(); //Setup Base URI
        utils.RestAssuredUtil.setBasePath("api"); //Setup Base Path
        utils.RestAssuredUtil.setContentType(ContentType.JSON); //Setup Content Type

        log.info("Member used: "+MEMBER_ID);
        log.info("Survey used: "+SURVEY_ID);
    }

    @AfterClass
    public void afterTest() {
        utils.RestAssuredUtil.resetBaseURI();
        utils.RestAssuredUtil.resetBasePath();
    }

    @Test
    public void getStatusCode() throws CsvException {
        log.info("Checking Status Code of "+className);

        if(TestUtil.checkIfMemberOrSurveyExists(TestUtil.surveys)){
            Assert.assertEquals(api.getStatusCode(), 200, "Http Status Code Check Failed!");
        }else{
            Assert.assertEquals(api.getStatusCode(), 500, "Http Status Code Check Failed!");
        }
    }
    @Test
    public void validateSchema(){
        log.info("Validating Schema of "+className);

        api.validateSchema()
                .assertThat()
                .body(matchesJsonSchema(new File("src/test/resources/jsonFiles/"+className+"Schema.json"))).log().body();
    }

}
