package com.titarenko.operations.impl;

import com.titarenko.exceptions.NoSuchOperationException;
import com.titarenko.operations.Operation;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class OperationsService {

    private static final List<Operation> OPERATIONS = new ArrayList<Operation>() {{
        add(new Sum());
        add(new Minus());
        add(new Multiply());
        add(new Divide());
    }};

    public Operation obtainOperation(char ch) {
        return obtainOperation(String.valueOf(ch));
    }

    public Operation obtainOperation(CharSequence ch) {
        return OPERATIONS.stream()
                .filter(operation -> ch.equals(operation.getChar()))
                .findFirst()
                .orElseThrow(() -> new NoSuchOperationException("Wrong operator: " + ch));
    }

    public boolean isOperationExists(char ch) {
        return isOperationExists(String.valueOf(ch));
    }

    public boolean isOperationExists(CharSequence ch) {
        return OPERATIONS.stream()
                .anyMatch(operation -> ch.equals(operation.getChar()));
    }

}
