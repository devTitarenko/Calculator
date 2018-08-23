package com.titarenko.parser;

public interface Parser<T> {
    T parse(String str);
}
