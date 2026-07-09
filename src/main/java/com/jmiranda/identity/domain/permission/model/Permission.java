package com.jmiranda.identity.domain.permission.model;

public class Permission {
    private String id;
    private String authority; // "GET:/users"
    private String httpMethod;
    private String urlPattern;
    private String stateId;

    public Permission(String id, String authority, String httpMethod, String urlPattern, String stateId) {
        this.id = id;
        this.authority = authority;
        this.httpMethod = httpMethod;
        this.urlPattern = urlPattern;
        this.stateId = stateId;
    }

    public String getId() {
        return id;
    }

    public String getAuthority() {
        return authority;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public String getUrlPattern() {
        return urlPattern;
    }

    public String getStateId() {
        return stateId;
    }
}
