package com.titarenko.parser;

import com.titarenko.Node;
import com.titarenko.operations.impl.OperationsService;

import java.util.LinkedList;

public class NodeParserImpl implements NodeParser {

    private OperationsService operations = new OperationsService();

    @Override
    public LinkedList<Node> parse(String str) {
        StringBuilder number = new StringBuilder();
        LinkedList<Node> list = new LinkedList<>();
        int openBracket = 0;
        for (char ch : str.toCharArray()) {
            if (operations.isOperationExists(ch)) {
                Node node = new Node(operations.obtainOperation(ch));
                node.setLeftValue(Double.valueOf(number.toString()));
                populate(list, node);
                number = new StringBuilder();
            } else if (ch == '(') {
                openBracket = list.size();
            } else if (ch == ')') {
                for (int i = openBracket; i < list.size(); i++) {
                    list.get(i).increaseAdditionalPriority();
                }
                openBracket = 0;
            } else {
                number.append(ch);
            }
        }
        list.getLast().setRightValue(Double.valueOf(number.toString()));
        return list;
    }

    private void populate(LinkedList<Node> list, Node node) {
        if (!list.isEmpty()) {
            list.getLast().setRightValue(node.getLeftValue());
        }
        list.addLast(node);
    }
}
