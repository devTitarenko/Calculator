package com.titarenko.calculator;

import com.titarenko.Node;
import com.titarenko.parser.NodeParser;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleCalculator implements Calculator {

    private static final Pattern PATTERN = Pattern.compile("\\((?<bracket>[+-/*\\d]+)\\)");
    private NodeParser nodeParser;

    public SimpleCalculator(NodeParser nodeParser) {
        this.nodeParser = nodeParser;
    }

    @Override
    public Double calculate(String string) {
        LinkedList<Node> nodeLinkedList = nodeParser.parse(processBrackets(string));
        List<Node> sortedByPriority = getSortedNodes(nodeLinkedList);
        sortedByPriority.forEach(node -> processOperation(nodeLinkedList, node));
        return nodeLinkedList.getLast().getValue();
    }

    private String processBrackets(String string) {
        Matcher matcher = PATTERN.matcher(string);
        while (matcher.find()) {
            String bracket = matcher.group("bracket");
            string = string.replace("(" + bracket + ")", String.valueOf(calculate(bracket)));
            matcher = PATTERN.matcher(string);
        }
        return string;
    }

    private List<Node> getSortedNodes(LinkedList<Node> nodeLinkedList) {
        List<Node> sortedByPriority = new ArrayList<>(nodeLinkedList);
        sortedByPriority.sort(Comparator.comparing(Node::getOperation));
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
