package utils;

import io.restassured.response.ValidatableResponse;

public interface ApiInterface {
    int getStatusCode();
    ValidatableResponse validateSchema();
}
