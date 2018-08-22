package com.titarenko;

import com.titarenko.calculator.CalculatorFactory;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String calculatorName = args.length > 1 ? args[0] : "SimpleCalculator";
        Scanner input = new Scanner(System.in);

        while (true) {
            String string = input.nextLine();
            if (Validator.isValid(string)) {
                System.out.println(CalculatorFactory.produceCalculator(calculatorName).calculate(string));
            } else {
                System.err.println("Wrong format");
            }
        }
    }
}
