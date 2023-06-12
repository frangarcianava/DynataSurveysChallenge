package api;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import utils.TestUtil;

import java.util.HashMap;
import java.util.Map;

public class BaseMethods {

    TestUtil testUtil = new TestUtil();
    public int statusCode;
    public Map<String, Object> params = new HashMap<>();

    public int validateStatusCode(String endpoint) {
        return testUtil.checkStatusCode(getResponse(endpoint));
    }

    public int validateStatusCodeUsingParams(String endpoint, Map<String, Object> queryParams) {
        statusCode = testUtil.checkStatusCode(getResponseUsingParams(endpoint,queryParams));
        return statusCode;
    }

    public Response getResponse(String endpoint){
        return utils.RestAssuredUtil.getResponse(endpoint);
    }

    public Response getResponseUsingParams(String endpoint, Map<String, Object> queryParams){
        return utils.RestAssuredUtil.getResponseWithParams(endpoint,queryParams);
    }

    public ValidatableResponse validateSchema(String endpoint){
        return testUtil.checkJSONSchema(getResponse(endpoint));
    }

    public ValidatableResponse validateSchemaUsingParams(String endpoint, Map<String, Object> queryParams){
        return testUtil.checkJSONSchema(getResponseUsingParams(endpoint,queryParams));
    }
}
