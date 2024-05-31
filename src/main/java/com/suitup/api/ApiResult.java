package com.suitup.api;


import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ApiResult {
    private final int statusCode;
    private final JsonPath body;
    private final long time;
    private final String curl;

    public ApiResult(int code, JsonPath body, long time, String curl) {
        this.statusCode = code;
        this.body = body;
        this.time = time;
        this.curl = curl;
    }
    public ApiResult(Response response, String curl) {
        this.statusCode = response.getStatusCode();
        this.body = response.jsonPath();
        this.time = response.getTime();
        this.curl = curl;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public JsonPath getBody() {
        return body;
    }
    public <T> T getObjectFromBody(String path) {
        return body.get(path);
    }
    public long getTime() {
        return time;
    }
    public String getCurl() {
        return curl;
    }
    @Override
    public String toString() {
        return "ApiResponse{" +
                "status code=" + statusCode +
                ", body=" + body.get().toString().replace("\n"," ") +
                ", time=" + time +
                "ms}";
    }
}