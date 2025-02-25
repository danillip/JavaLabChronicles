package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Пока обрабатываем только + и -.
 * TODO: В следующих коммитах - добавить * /, скобки, проверку на ошибки.
 */
public class EvaluatorKalc {

    /**
     * Преобразуем строку в список (число/оператор).
     * @param expr выражение (например, "1+2-3")
     * @return список строковых токенов
     */
    public static List<String> tokenize(String expr) {
        String[] tokens = expr.split("(?=[+-])|(?<=[+-])");
        List<String> list = new ArrayList<>();
        for (String token : tokens) {
            // Удаляем лишние пробелы
            token = token.trim();
            if (!token.isEmpty()) {
                list.add(token);
            }
        }
        return list;
    }

    /**
     * Простейшее вычисление (только + -).
     * @param expr выражение
     * @return результат вычисления или null, если ошибка
     */
    public static Double evaluate(String expr) {
        // TODO: обработка ошибок, проверка корректности
        List<String> tokens = tokenize(expr);
        if (tokens.isEmpty()) {
            return null;
        }

        // Начнём с первого числа
        double result;
        try {
            result = Double.parseDouble(tokens.get(0));
        } catch (NumberFormatException e) {
            // Некорректное первое число
            return null;
        }

        // Далее проходим по токенам
        for (int i = 1; i < tokens.size(); i += 2) {
            String op = tokens.get(i);
            double val;
            try {
                val = Double.parseDouble(tokens.get(i+1));
            } catch (Exception e) {
                return null;
            }
            switch (op) {
                case "+" -> result += val;
                case "-" -> result -= val;
                default -> {
                    // Встретился неизвестный оператор
                    return null;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("=== EvaluatorKalc v0.2 ===");
        String testExpr = "1 + 2 - 3 + 10";
        Double result = evaluate(testExpr);
        System.out.println("Выражение: " + testExpr);
        System.out.println("Результат: " + result);
    }
}