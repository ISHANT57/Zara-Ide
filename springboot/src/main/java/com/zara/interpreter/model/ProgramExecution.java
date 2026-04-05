package com.zara.interpreter.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProgramExecution {
    private String id;
    private String code;
    private List<String> output;
    private String status; // "success", "error"
    private String errorMessage;
    private LocalDateTime executedAt;
    private long executionTimeMs;

    public ProgramExecution(String id, String code) {
        this.id = id;
        this.code = code;
        this.output = new ArrayList<>();
        this.status = "running";
        this.executedAt = LocalDateTime.now();
        this.executionTimeMs = 0;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<String> getOutput() {
        return output;
    }

    public void setOutput(List<String> output) {
        this.output = output;
    }

    public void addOutput(String line) {
        this.output.add(line);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public LocalDateTime getExecutedAt() {
        return executedAt;
    }

    public void setExecutedAt(LocalDateTime executedAt) {
        this.executedAt = executedAt;
    }

    public long getExecutionTimeMs() {
        return executionTimeMs;
    }

    public void setExecutionTimeMs(long executionTimeMs) {
        this.executionTimeMs = executionTimeMs;
    }
}
