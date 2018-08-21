package com.titarenko;

import com.titarenko.calculator.Calculator;
import com.titarenko.calculator.CalculatorImpl;
import com.titarenko.parser.Parser;
import com.titarenko.parser.SimpleParserImpl;

import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        Parser parser = new SimpleParserImpl();
        LinkedList<Node> collection = parser.parse(str);

        Calculator calculator = new CalculatorImpl();
        System.out.println(calculator.calculate(collection));
    }
}
