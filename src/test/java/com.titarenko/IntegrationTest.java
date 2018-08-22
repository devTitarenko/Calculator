package com.titarenko;

import com.titarenko.calculator.Calculator;
import com.titarenko.calculator.SimpleCalculator;
import com.titarenko.parser.NodeParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IntegrationTest {

    private Calculator calculator;

    @BeforeEach
    void setup() {
        calculator = new SimpleCalculator(new NodeParser());
    }

    @Test
    void testSimpleCalculatorWithNodeParser() {
        assertTrue(0.0000000001 > Math.abs(12.6 - (double) calculator.calculate("14-8+1.1*(2+4)")));
        assertEquals(5D, calculator.calculate("20/((3+2)-1.5)*2"));
    }
}
