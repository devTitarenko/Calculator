package com.titarenko;

import com.titarenko.operations.Operation;
import com.titarenko.operations.impl.OperationsService;

import java.util.Objects;

public class Node {

    private Operation operation;
    private Double leftValue;
    private Double rightValue;
    private Double value;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(getLeftValue(), node.getLeftValue()) &&
                Objects.equals(getRightValue(), node.getRightValue()) &&
                Objects.equals(getValue(), node.getValue()) &&
                Objects.equals(operation, node.operation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLeftValue(), getRightValue(), getValue(), operation);
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
