package com.zara.interpreter.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExecutionRequest {
    @JsonProperty("code")
    private String code;

    @JsonProperty("sessionId")
    private String sessionId;

    public ExecutionRequest() {
    }

    public ExecutionRequest(String code) {
        this.code = code;
    }

    public ExecutionRequest(String code, String sessionId) {
        this.code = code;
        this.sessionId = sessionId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
