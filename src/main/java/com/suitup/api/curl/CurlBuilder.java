package com.suitup.api.curl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.suitup.api.configuration.EnvironmentConfiguration;

import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;


public class CurlBuilder {
    static String generateQueryParameters(Map<String, Object> requestParameters) {
        StringBuilder queryParamsBuilder = new StringBuilder();
        requestParameters.forEach((key, value) ->
                queryParamsBuilder.append(key).append("=").append(value).append("&"));
        return queryParamsBuilder.toString();
    }

    public static String createCurl(String requestMethod, String path,
                                    Map<String, Object> requestParameters,
                                    Map<String, Object> requestBody,
                                    Map<String, String> requestHeaders){
        StringBuilder curlBuilder = new StringBuilder("curl ");
        // Append base URL and path
        curlBuilder.append("'").append(EnvironmentConfiguration.getBaseUrl())
                .append("/").append(path).append("' \\ \n");
        curlBuilder.append("-X '").append(requestMethod.toUpperCase()).append("' \\");
        // Append headers
        if (requestHeaders != null && !requestHeaders.isEmpty()) {
            requestHeaders.forEach((key, value) ->
                    curlBuilder.append("-H '").append(key).append(": ").append(value).append("' "));
        }
        // Append query parameters
        if (requestParameters != null && !requestParameters.isEmpty()) {
            curlBuilder.append("'").append(generateQueryParameters(requestParameters)).append("' ");
        }
        // Append request body
        if (requestBody != null && !requestBody.isEmpty()) {
            try {
                String jsonBody = new ObjectMapper().writeValueAsString(requestBody);
                curlBuilder.append("-d '").append(jsonBody).append("' \n");
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }        }

        return curlBuilder.toString();
    }
}
