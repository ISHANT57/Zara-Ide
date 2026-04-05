package com.zara.interpreter.core;

public interface Expression {
    // Returns Double for numbers, String for text, Boolean for comparisons
    Object evaluate(Environment env);
}
