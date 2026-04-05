package com.zara.interpreter.service;

import com.zara.interpreter.core.Interpreter;
import com.zara.interpreter.core.OutputCapture;
import com.zara.interpreter.model.ExecutionResponse;
import com.zara.interpreter.model.ProgramExecution;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InterpreterService {
    private final Map<String, List<ProgramExecution>> sessionHistory = new HashMap<>();
    private final Map<String, ProgramExecution> executionCache = new HashMap<>();

    public ExecutionResponse executeCode(String code, String sessionId) {
        long startTime = System.currentTimeMillis();
        String executionId = UUID.randomUUID().toString();

        try {
            OutputCapture capture = new OutputCapture();
            capture.start();

            Interpreter interpreter = new Interpreter();
            interpreter.run(code);

            capture.stop();

            long executionTime = System.currentTimeMillis() - startTime;

            ProgramExecution execution = new ProgramExecution(executionId, code);
            execution.setOutput(capture.getOutput());
            execution.setStatus("success");
            execution.setExecutionTimeMs(executionTime);

            // Store in session history
            if (sessionId != null && !sessionId.isEmpty()) {
                sessionHistory.computeIfAbsent(sessionId, k -> new ArrayList<>()).add(execution);
            }

            executionCache.put(executionId, execution);

            return new ExecutionResponse(
                    true,
                    capture.getOutput(),
                    null,
                    executionTime,
                    executionId
            );

        } catch (Exception e) {
            long executionTime = System.currentTimeMillis() - startTime;

            ProgramExecution execution = new ProgramExecution(executionId, code);
            execution.setStatus("error");
            execution.setErrorMessage(e.getMessage());
            execution.setExecutionTimeMs(executionTime);

            if (sessionId != null && !sessionId.isEmpty()) {
                sessionHistory.computeIfAbsent(sessionId, k -> new ArrayList<>()).add(execution);
            }

            executionCache.put(executionId, execution);

            return new ExecutionResponse(
                    false,
                    new ArrayList<>(),
                    e.getMessage(),
                    executionTime,
                    executionId
            );
        }
    }

    public List<ProgramExecution> getSessionHistory(String sessionId) {
        return sessionHistory.getOrDefault(sessionId, new ArrayList<>());
    }

    public ProgramExecution getExecution(String executionId) {
        return executionCache.get(executionId);
    }

    public void clearSessionHistory(String sessionId) {
        sessionHistory.remove(sessionId);
    }

    public int getSessionCount() {
        return sessionHistory.size();
    }
}
