package com.titarenko.calculator;

import com.titarenko.exceptions.NoSuchCalculatorException;
import com.titarenko.parser.NodeParser;

public class CalculatorFactory {

    public static Calculator produceCalculator(String calculatorName) {
        if ("SimpleCalculator".equalsIgnoreCase(calculatorName)) {
            return new SimpleCalculator(new NodeParser());
        } else if ("JavaxCalculator".equalsIgnoreCase(calculatorName)) {
            return new JavaxCalculator();
        } else {
            throw new NoSuchCalculatorException("Calculator " + calculatorName + " does not exist.");
        }
    }

}
