package com.titarenko;

public abstract class Validator {

    public static boolean isValid(String expression) {
        return expression.matches("^\\(?\\d+([+-/*]\\(?\\d+\\)?)+$");
    }

}
