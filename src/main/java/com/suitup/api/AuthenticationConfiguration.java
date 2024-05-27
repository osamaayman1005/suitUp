package com.suitup.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class AuthenticationConfiguration {
    private static RequestSpecification authenticationSpecification;
    public static void setAuthenticationToken(String authenticationKey, String authenticationValue){
        authenticationSpecification = new RequestSpecBuilder().
                addHeader(authenticationKey, authenticationValue).build();
    }
    public static void setBearerAuthenticationToken(String token){
        setAuthenticationToken("authentication", "bearer " + token);
    }
    public static void removeAuthentication(String token){
        authenticationSpecification = null;
    }
    public static RequestSpecification getAuthenticationSpecification() {
        return authenticationSpecification;
    }

}
