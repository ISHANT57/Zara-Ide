package com.zara.interpreter.core;

public class AssignInstruction implements Instruction {
    private String varName;
    private Expression value;

    public AssignInstruction(String varName, Expression value) {
        this.varName = varName;
        this.value = value;
    }

    @Override
    public void execute(Environment env) {
        Object result = value.evaluate(env);
        env.set(varName, result);
    }
}
