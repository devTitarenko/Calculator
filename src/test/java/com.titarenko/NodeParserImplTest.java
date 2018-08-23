package com.titarenko;

import com.titarenko.parser.NodeParserImpl;
import com.titarenko.parser.NodeParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NodeParserImplTest {

    private NodeParser nodeParser;
    private static final String SUM = "+";
    private static final String MINUS = "-";
    private static final String DIVIDE = "/";
    private static final String MULTIPLY = "*";
    private static final String INPUT_1 = "128/4+71-13*2.5+18";

    @BeforeEach
    void setup() {
        nodeParser = new NodeParserImpl();
    }

    @Test
    void testParse() {
        LinkedList<Node> nodes = new LinkedList<Node>() {{
            add(new Node.NodeBuilder(DIVIDE).withLeftValue(128D).withRightValue(4D).build());
            add(new Node.NodeBuilder(SUM).withLeftValue(4D).withRightValue(71D).build());
            add(new Node.NodeBuilder(MINUS).withLeftValue(71D).withRightValue(13D).build());
            add(new Node.NodeBuilder(MULTIPLY).withLeftValue(13D).withRightValue(2.5D).build());
            add(new Node.NodeBuilder(SUM).withLeftValue(2.5D).withRightValue(18D).build());
        }};

        assertEquals(nodes, nodeParser.parse(INPUT_1));
    }

}
