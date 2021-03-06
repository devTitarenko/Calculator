package com.titarenko.operations.impl;

import com.titarenko.operations.Operation;

class Multiply implements Operation {

    private static final CharSequence CHAR = "*";
    private static final Integer PRIORITY = 2;

    @Override
    public Double execute(Double left, Double right) {
        return left * right;
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
