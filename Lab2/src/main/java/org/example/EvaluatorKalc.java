package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Обработка операций +, -, *, / без скобок
 * TODO: В следующем коммите добавить поддержку скобок
 */
public class EvaluatorKalc {

    /**
     * Разбивает выражение на токены (числа и операторы)
     * @param expr строка выражения
     * @return список токенов
     */
    public static List<String> tokenize(String expr) {
        // Разделяем по +, -, *, /
        // Но чтобы символы операторов не терять, используем регулярку
        // "(?=[+\\-*/])|(?<=[+\\-*/])"
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

    /**
     * Выполняем вычисление с учётом приоритета * и / перед + и -
     * @param expr входное выражение
     * @return число-результат (null, если ошибка)
     */
    public static Double evaluate(String expr) {
        List<String> tokens = tokenize(expr);
        if (tokens.isEmpty()) return null;

        // Сначала обработаем * и / получим промежуточный список
        List<String> processed = new ArrayList<>();
        Double prevValue = null;
        String prevOp = null;

        for (String token : tokens) {
            if (isOperator(token)) {
                prevOp = token;
            } else {
                // число
                double val;
                try {
                    val = Double.parseDouble(token);
                } catch (NumberFormatException e) {
                    return null;
                }
                if (prevOp == null) {
                    // первое число
                    prevValue = val;
                } else if (prevOp.equals("*")) {
                    prevValue = (prevValue == null) ? val : (prevValue * val);
                    prevOp = null;
                } else if (prevOp.equals("/")) {
                    if (val == 0) {
                        // Деление на ноль
                        return null;
                    }
                    prevValue = (prevValue == null) ? val : (prevValue / val);
                    prevOp = null;
                } else {
                    // Если + или - то сохраняем предыдущий результат и оператор
                    processed.add(String.valueOf(prevValue));
                    processed.add(prevOp);
                    prevValue = val;
                    prevOp = null;
                }
            }
        }
        // Добавляем последнее вычисленное число
        if (prevValue != null) {
            processed.add(String.valueOf(prevValue));
        }

        // Теперь в processed остались числа и операторы +/-
        double result;
        try {
            result = Double.parseDouble(processed.get(0));
        } catch (Exception e) {
            return null;
        }

        for (int i = 1; i < processed.size(); i += 2) {
            String op = processed.get(i);
            double val = Double.parseDouble(processed.get(i+1));
            if (op.equals("+")) {
                result += val;
            } else if (op.equals("-")) {
                result -= val;
            }
        }
        return result;
    }

    /**
     * Проверка, является ли токен оператором
     */
    private static boolean isOperator(String token) {
        return "+".equals(token) || "-".equals(token)
                || "*".equals(token) || "/".equals(token);
    }

    public static void main(String[] args) {
        System.out.println("=== EvaluatorKalc v0.3 ===");
        String testExpr = "2 + 3 * 4 - 10 / 5";
        Double result = evaluate(testExpr);
        System.out.println("Выражение: " + testExpr);
        System.out.println("Результат: " + result);
    }
}
