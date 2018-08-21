package com.titarenko.operations;

public interface Operation {

    Double execute(Double left, Double right);

    CharSequence getChar();

    Integer getPriority();
}
