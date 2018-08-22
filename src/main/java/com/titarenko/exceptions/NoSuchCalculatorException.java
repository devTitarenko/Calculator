package com.titarenko.exceptions;

public class NoSuchCalculatorException extends RuntimeException {

    public NoSuchCalculatorException(String message) {
        super(message);
    }

}
