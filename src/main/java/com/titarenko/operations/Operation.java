package com.titarenko.operations;

public interface Operation extends Comparable<Operation> {

    Double execute(Double left, Double right);

    CharSequence getChar();

    Integer getPriority();

    @Override
    default int compareTo(Operation o) {
        return o.getPriority() - getPriority();
    }

}
