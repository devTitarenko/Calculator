package com.titarenko;

import com.titarenko.calculator.CalculatorFactory;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String calculatorName = args.length > 0 ? args[0] : "SimpleCalculator";
        Scanner input = new Scanner(System.in);

        boolean isContinue = true;
        while (isContinue) {
            String string = input.nextLine();
            if (Validator.isValid(string)) {
                System.out.println(new CalculatorFactory().produceCalculator(calculatorName).calculate(string));
            } else if ("exit".equals(string)) {
                isContinue = false;
            } else {
                System.err.println("Wrong format");
            }
        }
    }
}
