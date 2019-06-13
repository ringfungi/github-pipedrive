package com.example.githubclient;

public interface APIConfiguration {
    static final String COMPANY_DOMAIN = "xpandit-606416";
    static final String API_BASE_URL_PD = "https://" + COMPANY_DOMAIN + ".pipedrive.com/";
    static final String API_BASE_URL_GH = "https://api.github.com/";
    static final String API_VERSION_SPEC_GH = "application/vnd.github.v3+json";
    static final String JSON_CONTENT_TYPE = "application/json";
}