package com.titarenko.operations.impl;

import com.titarenko.operations.Operation;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class OperationStrategy {

    public static final List<Operation> OPERATIONS = new ArrayList<Operation>() {{
        add(new Sum());
        add(new Minus());
        add(new Multiply());
        add(new Divide());
    }};

    public Operation obtainOperation(CharSequence ch) {
        return OPERATIONS.stream()
                .filter(operation -> ch.equals(operation.getChar()))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

}
