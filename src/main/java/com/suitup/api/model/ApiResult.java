package com.suitup.api.model;


import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.asserts.SoftAssert;
import io.restassured.module.jsv.JsonSchemaValidator;


public class ApiResult {
    private final Response response;
    private final String curl;

    public ApiResult(Response response, String curl) {
        this.response = response;
        this.curl = curl;
    }

    public int getStatusCode() {
        return response.getStatusCode();
    }

    public JsonPath getBody() {
        return response.jsonPath();
    }
    public <T> T getObjectFromBody(String path) {
        return response.jsonPath().get(path);
    }
    public long getTime() {
        return response.getTime();
    }
    public String getCurl() {
        return curl;
    }
    @Override
    public String toString() {
        return "ApiResponse{" +
                "status code=" + response.getStatusCode() +
                ", body=" + response.jsonPath().get().toString().replace("\n"," ") +
                ", time=" + response.getTime() +
                "ms}";
    }
    public void validateResponseSchema(String schema, SoftAssert softAssert) {
        try {
            response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(schema));
            softAssert.assertTrue(true, "Schema validation passed");
        } catch (AssertionError e) {
            softAssert.fail("Schema validation failed: " + e.getMessage());
        }
    }
}