package com.titarenko.calculator;

import com.titarenko.Node;
import com.titarenko.parser.Parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

public class SimpleCalculator implements Calculator {

    private Parser parser;

    public SimpleCalculator(Parser parser) {
        this.parser = parser;
    }

    @Override
    public Number calculate(String string) {
        LinkedList<Node> nodeLinkedList = parser.parse(string);
        List<Node> sortedByPriority = getSortedNodes(nodeLinkedList);
        sortedByPriority.forEach(node -> processOperation(nodeLinkedList, node));
        return nodeLinkedList.getLast().getValue();
    }

    private List<Node> getSortedNodes(LinkedList<Node> nodeLinkedList) {
        List<Node> sortedByPriority = new ArrayList<>(nodeLinkedList);
        sortedByPriority.sort((node1, node2) ->
                (node2.getAdditionalPriority() + node2.getOperation().getPriority())
                        - (node1.getAdditionalPriority() + node1.getOperation().getPriority()));
        return sortedByPriority;
    }

    private void processOperation(LinkedList<Node> list, Node node) {
        int index = list.indexOf(node);
        Double value = node.getOperation().execute(node.getLeftValue(), node.getRightValue());
        if (index > 0) {
            list.get(index - 1).setRightValue(value);
        }
        if (index < list.size() - 1) {
            list.get(index + 1).setLeftValue(value);
        }
        if (list.size() > 1) {
            list.remove(node);
        } else {
            node.setValue(value);
        }
    }

}
