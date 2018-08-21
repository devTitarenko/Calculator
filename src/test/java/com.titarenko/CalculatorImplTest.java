package com.titarenko;

import com.titarenko.calculator.Calculator;
import com.titarenko.calculator.CalculatorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculatorImplTest {

    private Calculator calculator;
    private LinkedList<Node> nodes;

    @BeforeEach
    void setup() {
        calculator = new CalculatorImpl();
        nodes = new LinkedList<>();
    }

    @Test
    void testCalculate() {
        nodes.add(new Node.NodeBuilder("/").withLeftValue(128D).withRightValue(4D).build());
        nodes.add(new Node.NodeBuilder("+").withLeftValue(4D).withRightValue(71D).build());
        nodes.add(new Node.NodeBuilder("-").withLeftValue(71D).withRightValue(13D).build());
        nodes.add(new Node.NodeBuilder("*").withLeftValue(13D).withRightValue(2.5D).build());
        nodes.add(new Node.NodeBuilder("+").withLeftValue(2.5D).withRightValue(18D).build());

        assertEquals(88.5, (double) calculator.calculate(nodes));
    }

    @Test
    void testCalculateDivideByZero() {
        nodes.add(new Node.NodeBuilder("/").withLeftValue(128D).withRightValue(0D).build());

        assertThrows(ArithmeticException.class, () -> calculator.calculate(nodes), "/ by zero");
    }
}
