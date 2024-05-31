package com.suitup.api;

import io.restassured.RestAssured;

import java.util.HashMap;
import java.util.Map;

public abstract class ApiRequestExecutor {
    /*
     ################### POST ###################
     */
    public static ApiResult post(String path, Map<String, Object> requestBody){
        return post(path,requestBody, new HashMap<>());
    }
    public static ApiResult post(String path, Map<String, Object> requestBody,
                                 Map<String, String> requestHeaders){
        requestHeaders.putAll(AuthenticationConfiguration.getAuthenticationTokens());
        String curl = CurlBuilder.createCurl("post",
                path,null,requestBody,requestHeaders);
        return new ApiResult(RestAssured.given().when()
                .headers(requestHeaders)
                .baseUri(EnvironmentConfiguration.getBaseUrl())
                .body(requestBody)
                .post(path), curl);
    }
    /*
    ################### PUT ###################
    */
    public static ApiResult put(String path, Map<String, Object> requestBody){
        return put(path, requestBody, new HashMap<>());
    }
    public static ApiResult put(String path, Map<String, Object> requestBody,
                                Map<String, String> requestHeaders){
        requestHeaders.putAll(AuthenticationConfiguration.getAuthenticationTokens());
        String curl = CurlBuilder.createCurl("put",
                path,null,requestBody,requestHeaders);
        return new ApiResult(RestAssured.given().when()
                .headers(requestHeaders)
                .baseUri(EnvironmentConfiguration.getBaseUrl())
                .body(requestBody)
                .put(path), curl);
    }
    /*
    ################### PATCH ###################
     */
    public static ApiResult patch(String path, Map<String, Object> requestBody){
        return patch(path, requestBody, new HashMap<>());
    }
    public static ApiResult patch(String path, Map<String, Object> requestBody,
                                  Map<String, String> requestHeaders){
        requestHeaders.putAll(AuthenticationConfiguration.getAuthenticationTokens());
        String curl = CurlBuilder.createCurl("patch",
                path,null,requestBody,requestHeaders);
        return new ApiResult(RestAssured.given().when()
                .headers(requestHeaders)
                .baseUri(EnvironmentConfiguration.getBaseUrl())
                .body(requestBody)
                .patch(path), curl);
    }
    /*
    ################### GET ###################
     */
    public static ApiResult get(String path){
        return get(path, new HashMap<>(), new HashMap<>());
    }
    public static ApiResult get(String path, Map<String, Object> requestParameters){
        return get(path, requestParameters, new HashMap<>());
    }
    public static ApiResult get(String path, Map<String, Object> requestParameters,
                                Map<String, String> requestHeaders){
        requestHeaders.putAll(AuthenticationConfiguration.getAuthenticationTokens());
        String curl = CurlBuilder.createCurl("get",
                path,requestParameters,null,requestHeaders);
        return new ApiResult(RestAssured.given().when()
                .headers(requestHeaders)
                .baseUri(EnvironmentConfiguration.getBaseUrl())
                .params(requestParameters)
                .get(path), curl);
    }
    /*
    ################### DELETE ###################
    */
    public static ApiResult delete(String path){
        return delete(path, new HashMap<>());
    }
    public static ApiResult delete(String path, Map<String, String> requestHeaders){
        requestHeaders.putAll(AuthenticationConfiguration.getAuthenticationTokens());
        String curl = CurlBuilder.createCurl("delete",
                path,null,null,requestHeaders);
        return new ApiResult(
                RestAssured.given().when()
                        .headers(requestHeaders)
                        .baseUri(EnvironmentConfiguration.getBaseUrl())
                        .delete(path), curl);
    }

}
