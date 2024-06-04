package com.suitup.api.model;

import com.suitup.api.enums.HttpMethod;

import java.util.List;
import java.util.Map;

public class ApiRequest {
    private HttpMethod httpMethod;
    private String baseUrl;
    private String endpoint;
    private Map<String, String> headers;
    private Map<String, Object> parameters;
    private Map<String, Object> body;

    public ApiRequest(HttpMethod httpMethod, String baseUrl, String endpoint, Map<String, String> headers,
                      Map<String, Object> parameters, Map<String, Object> body) {
        this.httpMethod = (httpMethod != null) ? httpMethod : HttpMethod.GET;
        this.baseUrl = baseUrl;
        this.endpoint = endpoint;
        this.headers = headers;
        this.parameters = parameters;
        this.body = body;

    }

    public ApiRequest() {

    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    public Map<String, Object> getBody() {
            return body;
    }

    public void setBody(Map<String, Object> body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "ApiRequest{" +
                "httpMethod=" + httpMethod +
                ", baseUrl='" + baseUrl + '\'' +
                ", endpoint='" + endpoint + '\'' +
                ", headers=" + headers +
                ", parameters=" + parameters +
                ", body=" + body +
                '}';
    }
}
