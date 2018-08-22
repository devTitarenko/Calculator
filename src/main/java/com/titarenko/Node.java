package com.titarenko;

import com.titarenko.operations.Operation;
import com.titarenko.operations.impl.OperationsService;

import java.util.Objects;

public class Node {

    private Operation operation;
    private Double leftValue;
    private Double rightValue;
    private Double value;
    private int additionalPriority = 0;

    public Node(Operation operation) {
        this.operation = operation;
    }

    public Operation getOperation() {
        return operation;
    }

    public Double getLeftValue() {
        return leftValue;
    }

    public void setLeftValue(Double leftValue) {
        this.leftValue = leftValue;
    }

    public Double getRightValue() {
        return rightValue;
    }

    public void setRightValue(Double rightValue) {
        this.rightValue = rightValue;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public int getAdditionalPriority() {
        return additionalPriority;
    }

    public void increaseAdditionalPriority() {
        additionalPriority += 2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return additionalPriority == node.additionalPriority &&
                Objects.equals(operation, node.operation) &&
                Objects.equals(leftValue, node.leftValue) &&
                Objects.equals(rightValue, node.rightValue) &&
                Objects.equals(value, node.value);
    }

    @Override
    public int hashCode() {

        return Objects.hash(operation, leftValue, rightValue, value, additionalPriority);
    }

    public static class NodeBuilder {
        private Node node;

        public NodeBuilder(CharSequence ch) {
            node = new Node(new OperationsService().obtainOperation(ch));
        }

        public NodeBuilder withLeftValue(Double leftValue) {
            node.setLeftValue(leftValue);
            return this;
        }

        public NodeBuilder withRightValue(Double rightValue) {
            node.setRightValue(rightValue);
            return this;
        }

        public Node build() {
            return node;
        }
    }
}
