package com.titarenko.calculator;

import com.titarenko.Node;

import java.util.LinkedList;

public class CalculatorImpl implements Calculator {

    @Override
    public Double calculate(LinkedList<Node> nodes) {
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).getOperation().getPriority() > 1) {
                noName(nodes, i);
                if (nodes.size() > 1) {
                    nodes.remove(i--);
                }
            }
        }
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).getOperation().getPriority() == 1) {
                noName(nodes, i);
            }
        }
        return nodes.getLast().getValue();
    }

    private void noName(LinkedList<Node> list, Integer index) {
        Node node = list.get(index);
        Double value = node.getOperation().execute(node.getLeftValue(), node.getRightValue());
        node.setValue(value);
        if (index > 0) {
            list.get(index - 1).setRightValue(value);
        }
        if (index < list.size() - 1) {
            list.get(index + 1).setLeftValue(value);
        }
    }

}
