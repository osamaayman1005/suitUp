package com.suitup.api.enums;

public enum HttpMethod {
    POST ("post"),
    PUT("put"),
    PATCH("patch"),
    GET("get"),
    DELETE("delete"),
    OPTIONS("options");

    public final String value;
    HttpMethod(String value) {
        this.value = value;
    }
}
