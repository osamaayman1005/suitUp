package com.suitup.api;


import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ApiResponse {
    private final int statusCode;
    private final JsonPath body;
    private final long time;

    public ApiResponse(int code, JsonPath body, long time) {
        this.statusCode = code;
        this.body = body;
        this.time = time;
    }
    public ApiResponse(Response response) {
        this.statusCode = response.getStatusCode();
        this.body = response.jsonPath();
        this.time = response.getTime();
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

    @Override
    public String toString() {
        return "ApiResponse{" +
                "status code=" + statusCode +
                ", body=" + body.get().toString().replace("\n"," ") +
                ", time=" + time +
                "ms}";
    }
}