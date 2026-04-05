package com.zara.interpreter.core;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class OutputCapture {
    private ByteArrayOutputStream outputStream;
    private PrintStream printStream;
    private PrintStream originalOut;
    private List<String> outputLines;

    public OutputCapture() {
        this.outputStream = new ByteArrayOutputStream();
        this.printStream = new PrintStream(outputStream);
        this.originalOut = System.out;
        this.outputLines = new ArrayList<>();
    }

    public void start() {
        System.setOut(printStream);
        outputLines.clear();
    }

    public void stop() {
        printStream.flush();
        System.setOut(originalOut);
        
        String content = outputStream.toString();
        if (!content.isEmpty()) {
            String[] lines = content.split("\n");
            for (String line : lines) {
                if (!line.trim().isEmpty()) {
                    outputLines.add(line);
                }
            }
        }
    }

    public List<String> getOutput() {
        return outputLines;
    }

    public String getOutputAsString() {
        return String.join("\n", outputLines);
    }
}
