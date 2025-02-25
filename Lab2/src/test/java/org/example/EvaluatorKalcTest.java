package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EvaluatorKalcTest {

    @Test
    public void testSimplePlusMinus() {
        Double res = EvaluatorKalc.evaluate("1+2-3+10");
        assertNotNull(res);
        assertEquals(10, res, 1e-9);
    }

    @Test
    public void testMultDiv() {
        Double res = EvaluatorKalc.evaluate("2+3*4-10/5");
        assertNotNull(res);
        assertEquals(12, res, 1e-9);
    }

    @Test
    public void testParentheses() {
        // (2+3)*(4 - (10/5)) = 5 * (4 - 2) = 5 * 2 = 10
        Double res = EvaluatorKalc.evaluate("(2+3)*(4-(10/5))");
        assertNotNull(res);
        assertEquals(10, res, 1e-9);
    }
}