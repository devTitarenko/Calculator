package com.titarenko;

import com.titarenko.calculator.CalculatorFactory;
import com.titarenko.calculator.JavaxCalculator;
import com.titarenko.calculator.SimpleCalculator;
import com.titarenko.exceptions.NoSuchCalculatorException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculatorFactoryTest {

    private CalculatorFactory factory = new CalculatorFactory();

    @Test
    void testProduceCalculator() {
        assertEquals(JavaxCalculator.class, factory.produceCalculator("JavaxCalculator").getClass());
        assertEquals(SimpleCalculator.class, factory.produceCalculator("simplecalculator").getClass());
    }

    @Test
    void testException() {
        assertThrows(NoSuchCalculatorException.class,
                () -> factory.produceCalculator("Binar"), "Calculator `Binar` does not exist.");
    }
}
