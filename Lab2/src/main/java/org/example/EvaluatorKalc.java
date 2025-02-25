package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: проверить корректное количество скобок
 * UPD: сделать выброс ошибки (или возврат null) при неверном количестве скобок
 */
public class EvaluatorKalc {

    public static Double evaluate(String expr) {
        // Сначала обрабатываем скобки
        // Если находим '(', находим соответствующую ')'
        // и рекурсивно вычисляем содержимое
        return evaluateNoParentheses(resolveParentheses(expr));
    }

    /**
     * Находит самые "внутренние" скобки, вычисляет их содержимое
     * заменяет в исходном выражении на результат. Повторяет пока скобок нет
     * @param expr исходное выражение
     * @return новое выражение без скобок (или null при ошибке)
     */
    private static String resolveParentheses(String expr) {
        // Пока есть '('
        while (expr.contains("(")) {
            int startIndex = expr.lastIndexOf("(");
            int endIndex = expr.indexOf(")", startIndex);
            if (endIndex < 0) {
                // нет закрывающей скобки
                return null;
            }
            String inner = expr.substring(startIndex + 1, endIndex);
            // Рекурсивно вычисляем вызывая evaluateNoParentheses
            Double val = evaluateNoParentheses(inner);
            if (val == null) {
                return null;
            }
            // Заменяем ( ... ) на полученное значение
            expr = expr.substring(0, startIndex)
                    + val
                    + expr.substring(endIndex + 1);
        }
        // Если осталась ')', значит была ошибка
        if (expr.contains(")")) {
            return null;
        }
        return expr;
    }

    /**
     * Выполняет вычисление выражения без учёта скобок
     * Используем предыдущий метод обработки * /, затем + -
     * @param expr выражение без скобок
     * @return результат
     */
    private static Double evaluateNoParentheses(String expr) {
        if (expr == null) return null;
        List<String> tokens = tokenize(expr);
        if (tokens.isEmpty()) return null;
        // Сначала * /
        List<String> processed = new ArrayList<>();
        Double prevValue = null;
        String prevOp = null;

        for (String token : tokens) {
            if (isOperator(token)) {
                prevOp = token;
            } else {
                double val;
                try {
                    val = Double.parseDouble(token);
                } catch (Exception e) {
                    return null;
                }
                if (prevOp == null) {
                    prevValue = val;
                } else if (prevOp.equals("*")) {
                    prevValue = (prevValue == null) ? val : (prevValue * val);
                    prevOp = null;
                } else if (prevOp.equals("/")) {
                    if (val == 0) {
                        return null;
                    }
                    prevValue = (prevValue == null) ? val : (prevValue / val);
                    prevOp = null;
                } else {
                    // + или -
                    processed.add(String.valueOf(prevValue));
                    processed.add(prevOp);
                    prevValue = val;
                    prevOp = null;
                }
            }
        }

        if (prevValue != null) {
            processed.add(String.valueOf(prevValue));
        }

        // Теперь в processed только + и -
        double result;
        try {
            result = Double.parseDouble(processed.get(0));
        } catch (Exception e) {
            return null;
        }

        for (int i = 1; i < processed.size(); i += 2) {
            String op = processed.get(i);
            double val = Double.parseDouble(processed.get(i + 1));
            if (op.equals("+")) {
                result += val;
            } else if (op.equals("-")) {
                result -= val;
            }
        }
        return result;
    }

    private static List<String> tokenize(String expr) {
        String[] tokens = expr.split("(?=[+\\-*/])|(?<=[+\\-*/])");
        List<String> list = new ArrayList<>();
        for (String token : tokens) {
            token = token.trim();
            if (!token.isEmpty()) {
                list.add(token);
            }
        }
        return list;
    }

    private static boolean isOperator(String token) {
        return "+".equals(token) || "-".equals(token)
                || "*".equals(token) || "/".equals(token);
    }

    public static void main(String[] args) {
        String testExpr = "(2+3)*(4-(10/5))";
        Double result = evaluate(testExpr);
        System.out.println("Выражение: " + testExpr);
        System.out.println("Результат: " + result);
    }
}