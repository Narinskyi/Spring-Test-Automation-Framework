package com.onarinskyi.environment;

public class Api {

    private final String baseUrl;

    public Api(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}
