package com.github.osamaayman1005.api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseApi {

    private static String url;

    public static String getUrl() {
        return url;
    }
    public static void setUrl(String url) {
        BaseApi.url = url;
    }
    /*
        ################### POST ###################
         */
    public static ApiResponse post(String path, Map<String, Object> requestBody){
        return post(path,requestBody, new HashMap<>());
    }
    public static ApiResponse post(String path, Map<String, Object> requestBody,
                                      Map<String, String> requestHeaders){
        return new ApiResponse(RestAssured.given().when().spec(getSpecifications()).log().all()
                .headers(requestHeaders)
                .baseUri(url)
                .body(requestBody)
                .post(path));
    }
    /*
    ################### PUT ###################
    */
    public static ApiResponse put(String path, Map<String, Object> requestBody){
        return put(path, requestBody, new HashMap<>());
    }
    public static ApiResponse put(String path, Map<String, Object> requestBody,
                                     Map<String, String> requestHeaders){
        return new ApiResponse(RestAssured.given().when().spec(getSpecifications())
                .headers(requestHeaders)
                .baseUri(url)
                .body(requestBody)
                .put(path));
    }
    /*
    ################### PATCH ###################
     */
    public static ApiResponse patch(String path, Map<String, Object> requestBody){
        return patch(path, requestBody, new HashMap<>());
    }
    public static ApiResponse patch(String path, Map<String, Object> requestBody,
                                       Map<String, String> requestHeaders){
        return new ApiResponse(RestAssured.given().when().spec(getSpecifications())
                .headers(requestHeaders)
                .baseUri(url)
                .body(requestBody)
                .patch(path));
    }
    /*
    ################### GET ###################
     */
    public static ApiResponse get(String path){
        return get(path, new HashMap<>(), new HashMap<>());
    }
    public static ApiResponse get(String path, Map<String, Object> requestParameters){
        return get(path, requestParameters, new HashMap<>());
    }
    public static ApiResponse get(String path, Map<String, Object> requestParameters,
                                     Map<String, String> requestHeaders){
        return new ApiResponse(RestAssured.given().when().spec(getSpecifications())
                .headers(requestHeaders)
                .baseUri(url)
                .params(requestParameters)
                .get(path));
    }
    /*
    ################### DELETE ###################
    */
    public static ApiResponse delete(String path){
        return delete(path, new HashMap<>());
    }
    public static ApiResponse delete(String path,  Map<String, String> requestHeaders){
        return new ApiResponse(
                RestAssured.given().when().spec(getSpecifications())
                        .headers(requestHeaders)
                        .baseUri(url)
                        .delete(path));
    }
    private static RequestSpecification getSpecifications(){
    RequestSpecification requestSpecification;
    if (AuthenticationSpecification.getAuthenticationSpecification()!=null){
        requestSpecification = AuthenticationSpecification.getAuthenticationSpecification();
    }
    else {
        requestSpecification = new RequestSpecBuilder().build();
    }
    requestSpecification.header("Content-Type","application/json");
    return requestSpecification;
    }

}
