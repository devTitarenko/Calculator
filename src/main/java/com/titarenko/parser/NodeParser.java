package com.titarenko.parser;

import com.titarenko.Node;
import com.titarenko.operations.impl.OperationsService;

import java.util.LinkedList;

public class NodeParser implements Parser {

    private static final OperationsService OPERATIONS = new OperationsService();

    @Override
    public LinkedList<Node> parse(String str) {
        StringBuilder number = new StringBuilder();
        LinkedList<Node> list = new LinkedList<>();
        for (char ch : str.toCharArray()) {
            if (OPERATIONS.isOperationExists(ch)) {
                Node node = new Node(OPERATIONS.obtainOperation(ch));
                node.setLeftValue(Double.valueOf(number.toString()));
                populate(list, node);
                number = new StringBuilder();
            } else {
                number.append(ch);
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
