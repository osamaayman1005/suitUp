package com.suitup.api;

import java.util.HashMap;
import java.util.Map;

public class AuthenticationConfiguration {
    private static Map<String, String> authenticationTokens = new HashMap<>();
    public static void addAuthenticationToken(String authenticationKey, String authenticationValue){
        authenticationTokens.put(authenticationKey,authenticationValue);
    }
    public static void addBearerAuthenticationToken(String token){
        addAuthenticationToken("authentication", "bearer " + token);
    }
    public static void removeAuthenticationToken(String authenticationKey){
        authenticationTokens.remove(authenticationKey);
    }
    public static void removeAllAuthenticationTokens(){
        authenticationTokens = new HashMap<>();
    }
    public static Map<String, String> getAuthenticationTokens() {
        return authenticationTokens;
    }

}
