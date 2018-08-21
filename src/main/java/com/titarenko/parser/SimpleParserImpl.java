package com.titarenko.parser;

import com.titarenko.Node;
import com.titarenko.operations.impl.OperationStrategy;

import java.util.LinkedList;

public class SimpleParserImpl implements Parser {

    @Override
    public LinkedList<Node> parse(String str) {
        StringBuilder number = new StringBuilder();
        LinkedList<Node> list = new LinkedList<>();
        OperationStrategy strategy = new OperationStrategy();
        for (char ch : str.toCharArray()) {
            if (ch != '+' && ch != '-' && ch != '*' && ch != '/') {
                number.append(ch);
            } else {
                Node node = new Node(strategy.obtainOperation(ch + ""));
                node.setLeftValue(Double.valueOf(number.toString()));
                populate(list, node);
                number = new StringBuilder();
            }
        }
        list.getLast().setRightValue(Double.valueOf(number.toString()));
        return list;
    }

    private void populate(LinkedList<Node> list, Node node) {
        if (list.isEmpty()) {
            list.add(node);
        } else {
            list.getLast().setRightValue(node.getLeftValue());
            list.addLast(node);
        }
    }
}
