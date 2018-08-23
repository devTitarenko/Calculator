package com.titarenko;

import com.titarenko.calculator.Calculator;
import com.titarenko.calculator.JavaxCalculator;
import com.titarenko.calculator.SimpleCalculator;
import com.titarenko.parser.NodeParserImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IntegrationTest {

    private Calculator calculator;
    private static final Double COMPUTATIONAL_ERROR = 0.0000000001;

    @Test
    void testSimpleCalculatorWithNodeParser() {
        calculator = new SimpleCalculator(new NodeParserImpl());

        assertEquals(6.2, calculator.calculate("1+2*3-4/5"));
        assertEquals(22D, calculator.calculate("5*(2+3)-(9-3)/2"));
        assertEquals(64D, calculator.calculate("12+46.8/(2-3.2+3)*2"));
        checkComputationalError(12.6, (double) calculator.calculate("14-8+1.1*(2+4)"));
    }

    @Test
    void testJavaxCalculator() {
        calculator = new JavaxCalculator();

        assertEquals(6.2, calculator.calculate("1+2*3-4/5"));
        assertEquals(22, calculator.calculate("5*(2+3)-(9-3)/2"));
        assertEquals(-17.5, calculator.calculate("5*(2+(3-9)-3)/2"));
        assertEquals(64D, calculator.calculate("12+46.8/(2-3.2+3)*2"));
        checkComputationalError(12.6, (double) calculator.calculate("14-8+1.1*(2+4)"));
    }

    private void checkComputationalError(Double expected, Double actual) {
        assertTrue(COMPUTATIONAL_ERROR > Math.abs(expected - actual));
    }
}
