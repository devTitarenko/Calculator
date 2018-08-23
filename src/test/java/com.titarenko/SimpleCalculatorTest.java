package com.titarenko;

import com.titarenko.calculator.Calculator;
import com.titarenko.calculator.SimpleCalculator;
import com.titarenko.parser.NodeParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SimpleCalculatorTest {

    private Calculator calculator;
    private NodeParser nodeParser;
    private static final String SUM = "+";
    private static final String MINUS = "-";
    private static final String DIVIDE = "/";
    private static final String MULTIPLY = "*";
    private static final String INPUT_1 = "128/4+71-13*2.5+18";
    private static final String INPUT_2 = "128/0";

    @BeforeEach
    void setup() {
        nodeParser = mock(NodeParser.class);
        calculator = new SimpleCalculator(nodeParser);
    }

    @Test
    void testCalculate() {
        LinkedList<Node> nodes = new LinkedList<Node>() {{
            add(new Node.NodeBuilder(DIVIDE).withLeftValue(128D).withRightValue(4D).build());
            add(new Node.NodeBuilder(SUM).withLeftValue(4D).withRightValue(71D).build());
            add(new Node.NodeBuilder(MINUS).withLeftValue(71D).withRightValue(13D).build());
            add(new Node.NodeBuilder(MULTIPLY).withLeftValue(13D).withRightValue(2.5D).build());
            add(new Node.NodeBuilder(SUM).withLeftValue(2.5D).withRightValue(18D).build());
        }};
        when(nodeParser.parse(INPUT_1)).thenReturn(nodes);

        assertEquals(88.5, calculator.calculate(INPUT_1));
    }

    @Test
    void testCalculateDivideByZero() {
        LinkedList<Node> nodes = new LinkedList<Node>() {{
            add(new Node.NodeBuilder(DIVIDE).withLeftValue(128D).withRightValue(0D).build());
        }};
        when(nodeParser.parse(INPUT_2)).thenReturn(nodes);

        assertThrows(ArithmeticException.class, () -> calculator.calculate(INPUT_2), "/ by zero");
    }
}
