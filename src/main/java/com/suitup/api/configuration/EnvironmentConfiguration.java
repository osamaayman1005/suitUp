package com.suitup.api.configuration;

public class EnvironmentConfiguration {
    private static String baseUrl;

    public static String getBaseUrl() {
        return baseUrl;
    }
    public static void setBaseUrl(String baseUrl) {
        EnvironmentConfiguration.baseUrl = baseUrl;
    }
}
