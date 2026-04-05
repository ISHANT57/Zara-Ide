package com.zara.interpreter.core;

import java.util.List;

public class RepeatInstruction implements Instruction {
    private int times;
    private List<Instruction> body;

    public RepeatInstruction(int times, List<Instruction> body) {
        this.times = times;
        this.body = body;
    }

    @Override
    public void execute(Environment env) {
        for (int i = 0; i < times; i++) {
            for (Instruction instr : body) {
                instr.execute(env);
            }
        }
    }
}
