package com.titarenko.operations.impl;

import com.titarenko.operations.Operation;

class Divide implements Operation {

    private static final CharSequence CHAR = "/";
    private static final Integer PRIORITY = 2;

    @Override
    public Double execute(Double left, Double right) {
        if (right == 0) {
            throw new ArithmeticException("/ by zero");
        }
        return left / right;
    }

    @Override
    public CharSequence getChar() {
        return CHAR;
    }

    @Override
    public Integer getPriority() {
        return PRIORITY;
    }
}
