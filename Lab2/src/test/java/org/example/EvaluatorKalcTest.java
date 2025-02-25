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
        // 2 + 12 - 2 = 12
        assertNotNull(res);
        assertEquals(12, res, 1e-9);
    }
}