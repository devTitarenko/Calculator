package com.titarenko;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IntegrationTest {

    private static final String LINE_SEPARATOR = "\r\n";
    private static final Map<String, Double> DATA = new HashMap<String, Double>() {{
        put("1+2*3-4/5", 6.2);
        put("5*(2+3)-(9-3)/2", 22.0);
        put("5*(2+(3-9)-3)/2", -17.5);
        put("2*((-1-5))", -12.0);
        put("2*(-(-1-5))", 12.0);
        put("2*(-(-1-5)-2)", 8.0);
        put("5*(2+((3-9)*4-6)/3)/(-2)", 20.0);
        put("12+46.8/(2-3.2+3)*2", 64.0);

    }};
    private String expressions;
    private String answers;
    private ByteArrayOutputStream baos = new ByteArrayOutputStream();

    @BeforeEach
    void setup() {
        System.setOut(new PrintStream(baos));
        expressions = DATA.keySet().stream().map(exp -> exp + LINE_SEPARATOR).reduce((s1, s2) -> s1 + s2).orElse("");
        answers = DATA.values().stream().map(exp -> exp + LINE_SEPARATOR).reduce((s1, s2) -> s1 + s2).orElse("Wrong format");
    }

    @Test
    void testSimpleCalculator() {
        testMain(expressions, answers, "SimpleCalculator");
    }

    @Test
    void testJavaxCalculator() {
        testMain(expressions, answers, "javaxCALCULATOR");
    }

    private void testMain(String expression, String answer, String... args) {
        writeToInputStream(expression + "exit\n");
        Main.main(args);
        assertEquals(answer, readFromInputStream(), "Wrong answer for " + expression);
    }

    private void writeToInputStream(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    private String readFromInputStream() {
        return new String(baos.toByteArray());
    }
}
