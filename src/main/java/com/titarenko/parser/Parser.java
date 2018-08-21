package com.titarenko.parser;

import com.titarenko.Node;

import java.util.LinkedList;

public interface Parser {
    LinkedList<Node> parse(String str);
}
