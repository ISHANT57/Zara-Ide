package com.zara.interpreter.controller;

import com.zara.interpreter.model.ExecutionRequest;
import com.zara.interpreter.model.ExecutionResponse;
import com.zara.interpreter.model.ProgramExecution;
import com.zara.interpreter.service.InterpreterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class InterpreterController {

    @Autowired
    private InterpreterService interpreterService;

    @PostMapping("/execute")
    public ResponseEntity<ExecutionResponse> execute(@RequestBody ExecutionRequest request) {
        String sessionId = request.getSessionId();
        if (sessionId == null || sessionId.isEmpty()) {
            sessionId = UUID.randomUUID().toString();
        }

        ExecutionResponse response = interpreterService.executeCode(request.getCode(), sessionId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/history/{sessionId}")
    public ResponseEntity<List<ProgramExecution>> getHistory(@PathVariable String sessionId) {
        List<ProgramExecution> history = interpreterService.getSessionHistory(sessionId);
        return ResponseEntity.ok(history);
    }

    @GetMapping("/execution/{executionId}")
    public ResponseEntity<ProgramExecution> getExecution(@PathVariable String executionId) {
        ProgramExecution execution = interpreterService.getExecution(executionId);
        if (execution != null) {
            return ResponseEntity.ok(execution);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/history/{sessionId}")
    public ResponseEntity<Map<String, String>> clearHistory(@PathVariable String sessionId) {
        interpreterService.clearSessionHistory(sessionId);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Session history cleared");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "up");
        response.put("service", "ZARA Interpreter");
        response.put("version", "1.0.0");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("activeSessions", interpreterService.getSessionCount());
        return ResponseEntity.ok(stats);
    }
}
