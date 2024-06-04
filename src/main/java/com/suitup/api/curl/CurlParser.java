package com.suitup.api.curl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.suitup.api.enums.HttpMethod;
import com.suitup.api.model.ApiRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CurlParser{
    public static ApiRequest parseCurl(String curlString) {
        String cleanCurlString = curlString.replace("\n","");
        ApiRequest apiRequest = new ApiRequest();
        // Extract HTTP method (e.g., GET, POST, PUT)
        Pattern methodPattern = Pattern.compile("-X (\\w+)|--request (\\w+)");
        Matcher methodMatcher = methodPattern.matcher(cleanCurlString);
        if (methodMatcher.find()) {
            String httpMethod = methodMatcher.group(1) != null ?
                    methodMatcher.group(1) : methodMatcher.group(2);
            apiRequest.setHttpMethod(HttpMethod.valueOf(httpMethod.toUpperCase()));
        }

        // Extract URL (base URL + endpoint)
        Pattern urlPattern = Pattern.compile("curl .*?'(.*?)'");
        Matcher urlMatcher = urlPattern.matcher(cleanCurlString);
        if (urlMatcher.find()) {
            String url = urlMatcher.group(1);
            // Split URL into base URL and endpoint
            // Example: https://api.example.com/v1/users
            String[] urlParts = url.split("/");
            apiRequest.setBaseUrl(urlParts[0] + "//" + urlParts[2]);
            apiRequest.setEndpoint(url.substring(apiRequest.getBaseUrl().length()));

            // Extract GET parameters from URL
            int queryIndex = url.indexOf('?');
            if (queryIndex != -1) {
                String queryString = url.substring(queryIndex + 1);
                Map<String, Object> getParameters = parseParameters(queryString);
                apiRequest.setParameters(getParameters);
            }

        }

        // Extract headers (if any)
        // Example: -H 'Authorization: Bearer token123'
        Pattern headerPattern = Pattern.compile("-H '(.*?): (.*?)'|--header '(.*?): (.*?)'");
        Matcher headerMatcher = headerPattern.matcher(cleanCurlString);
        Map<String, String> headers = new HashMap<>();
        while (headerMatcher.find()) {
            String headerName = headerMatcher.group(1) != null ?
                    headerMatcher.group(1) : headerMatcher.group(3);
            String headerValue = headerMatcher.group(2) != null ?
                    headerMatcher.group(2) : headerMatcher.group(4);
            headers.put(headerName, headerValue);
        }
        if (!headers.isEmpty()) {
            apiRequest.setHeaders(headers);
        }

//        // Extract parameters (if any)
//        // Example: --data 'param1=value1&param2=value2'
//        Pattern paramPattern = Pattern.compile("(?<=https?://)[^?]+(\\?.*)?");
//        Matcher paramMatcher = paramPattern.matcher(cleanCurlString);
//        if (paramMatcher.find()) {
//            String paramsString = paramMatcher.group(1);
//            // Parse paramsString into a Map<String, Object>
//            Map<String, Object> parameters = parseParameters(paramsString);
//            apiRequest.setParameters(parameters);
//        }

        // Extract request body (if any)
        // Example: --data '{ "key": "value" }'
        Pattern bodyPattern = Pattern.compile("--data(?:-raw)? '(.*?)'|-d '(.*?)'");
        Matcher bodyMatcher = bodyPattern.matcher(cleanCurlString);
        if (bodyMatcher.find()) {
            if (apiRequest.getHttpMethod()== null){
                apiRequest.setHttpMethod(HttpMethod.POST);
            }
            String bodyString = bodyMatcher.group(1);
            // Parse bodyString into an Object (e.g., JSON or form data)
            Map<String, Object> requestBody = parseRequestBody(bodyString);
            apiRequest.setBody( requestBody);
        }
        if (apiRequest.getHttpMethod()==null){
            apiRequest.setHttpMethod(HttpMethod.GET);
        }
        return apiRequest;
    }

    private static Map<String, Object> parseParameters(String paramsString) {
        Map<String, Object> parameters = new HashMap<>();
        String[] keyValuePairs = paramsString.split("&");
        for (String pair : keyValuePairs) {
            String[] keyValue = pair.split("=");
            if (keyValue.length == 2) {
                String paramName = keyValue[0];
                String paramValue = keyValue[1];
                parameters.put(paramName, paramValue);
            }
        }
        return parameters;
    }

    private static Map<String, Object>  parseRequestBody(String bodyString) {
        try {
            // Parse the JSON body string into a Map (you can adapt this to your specific needs)
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> requestBody = objectMapper.readValue(bodyString, Map.class);
            return requestBody;
        } catch (Exception e) {
            // Handle any parsing errors (e.g., invalid JSON)
            e.printStackTrace();
            return null;
        }
    }

}

