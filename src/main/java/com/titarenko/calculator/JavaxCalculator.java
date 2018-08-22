package com.titarenko.calculator;

import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class JavaxCalculator implements Calculator {

    @Override
    public Number calculate(String string) {
        try {
            return (Number) new ScriptEngineManager().getEngineByName("JavaScript").eval(string);
        } catch (ScriptException e) {
            throw new RuntimeException(e);
        }
    }

}
