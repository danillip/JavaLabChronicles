package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Набор тестов для EvaluatorKalc.
 */
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

    @Test
    public void testParentheses() {
        // (2+3)*(4-(10/5)) = 5*(4-2) = 5*2 = 10
        Double res = EvaluatorKalc.evaluate("(2+3)*(4-(10/5))");
        assertNotNull(res);
        assertEquals(10, res, 1e-9);
    }

    @Test
    public void testFunctions() {
        // sin(0) = 0, cos(0) = 1, sqrt(4) = 2
        Double res = EvaluatorKalc.evaluate("sin(0) + cos(0) + sqrt(4)");
        assertNotNull(res);
        // 0 + 1 + 2 = 3
        assertEquals(3, res, 1e-9);
    }

    @Test
    public void testInvalidExpression() {
        // Некорректные скобки
        Double res = EvaluatorKalc.evaluate("(2+3*(4-2)");
        assertNull(res);
    }

    @Test
    public void testDivisionByZero() {
        Double res = EvaluatorKalc.evaluate("10/0");
        assertNull(res); // возвращаем null при делении на ноль
    }

    @Test
    public void testValidation() {
        // Выражение с запрещёнными символами
        boolean valid = EvaluatorKalc.isExpressionValid("2+3#4");
        assertFalse(valid);
    }
}