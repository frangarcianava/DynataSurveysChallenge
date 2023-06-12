package utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.*;

public class RestAssuredUtil {
    public static void setBaseURI() {
        baseURI = "http://localhost:8080/";
    }

    public static void setBasePath(String basePathTerm) {
        basePath = basePathTerm;
    }

    public static void resetBaseURI() {
        baseURI = null;
    }

    public static void resetBasePath() {
        basePath = null;
    }

    public static void setContentType(ContentType Type) {
        given().contentType(Type);
    }

    public static Response getResponse(String path) {
        return given().get(path);
    }

    public static Response getResponseWithParams(String path, Map<String, Object> queryParams) {
        return given()
                .pathParams(queryParams)
                .get(path);
    }

    public static int getSize(Response res){
        return  res
                .then()
                .extract()
                .jsonPath()
                .get("size()");
    }
}
