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
        for (char ch : str.toCharArray()) {
            if (operations.isOperationExists(ch) &&
                    !(ch == '-' && number.length() == 0 || "-".equals(number.toString()))) {
                Node node = new Node(operations.obtainOperation(ch));
                node.setLeftValue(obtainDoubleValue(number));
                populate(list, node);
                number = new StringBuilder();
            } else {
                number.append(ch);
            }
        }

        if (list.isEmpty()) {
            list.addLast(createSingleNumberNode(obtainDoubleValue(number)));
        } else {
            list.getLast().setRightValue(obtainDoubleValue(number));
        }
        return list;
    }

    private Node createSingleNumberNode(Double number) {
        return new Node.NodeBuilder("+").withLeftValue(0D).withRightValue(number).build();
    }

    private Double obtainDoubleValue(StringBuilder stringBuilder) {
        return Double.valueOf(stringBuilder.toString().replace("--", ""));
    }

    private void populate(LinkedList<Node> list, Node node) {
        if (!list.isEmpty()) {
            list.getLast().setRightValue(node.getLeftValue());
        }
        list.addLast(node);
    }
}
