package com.titarenko.calculator;

import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class JavaxCalculator implements Calculator {

    @Override
    public Double calculate(String string) {
        try {
            return Double.valueOf(new ScriptEngineManager().getEngineByName("JavaScript").eval(string) + "");
        } catch (ScriptException e) {
            throw new RuntimeException(e);
        }
    }

}
