package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Добавим функции sin, cos, sqrt (упрощённая реализация)
 * TODO: Расширить список функций, добавить поддержку нескольких аргументов и т.п
 */
public class EvaluatorKalc {

    // Хранилище значений переменных
    private static final Map<String, Double> variables = new HashMap<>();

    /**
     * Запрашиваем у пользователя значения переменных, если встречаются в выражении
     */
    public static void requestVariables(String expr) {
        // Ищем все подряд [a-zA-Z]+ как потенциальные переменные
        // (упрощённо без учёта уже сохранённых)
        Scanner sc = new Scanner(System.in);

        // Регуляркой найдём все слова из букв
        // \b([a-zA-Z]+)\b
        // Затем, если переменной ещё нет в мапе, запросим значение
        java.util.regex.Matcher matcher = java.util.regex.Pattern
                .compile("\\b([a-zA-Z]+)\\b")
                .matcher(expr);

        while (matcher.find()) {
            String var = matcher.group(1);
            if (!variables.containsKey(var)) {
                System.out.print("Введите значение для переменной " + var + ": ");
                double val = sc.nextDouble();
                variables.put(var, val);
            }
        }
    }

    /**
     * Заменяем все переменные на их значения в виде чисел (String).
     */
    public static String substituteVariables(String expr) {
        for (Map.Entry<String, Double> e : variables.entrySet()) {
            // Заменяем все вхождения e.getKey() на e.getValue()
            expr = expr.replaceAll("\\b" + e.getKey() + "\\b", e.getValue().toString());
        }
        return expr;
    }

    public static Double evaluateWithVars(String expr) {
        // Сначала просим пользователя ввести переменные
        requestVariables(expr);
        // Затем заменяем их
        String replaced = substituteVariables(expr);
        // И вызываем наш основной evaluate
        return evaluate(replaced);
    }

    // --- Далее всё остальное (evaluate, resolveParentheses, etc) ---

    public static Double evaluate(String expr) {
        // Сначала обрабатываем функции
        expr = resolveFunctions(expr);
        return evaluateNoParentheses(resolveParentheses(expr));
    }

    /**
     * Находим шаблоны вида sin( ... ), cos( ... ), sqrt( ... )
     * и вычисляем их содержимое рекурсивно.
     */
    private static String resolveFunctions(String expr) {
        if (expr == null) return null;
        // Ищем sin(...), cos(...), sqrt(...)
        String[] funcs = {"sin", "cos", "sqrt"};
        for (String func : funcs) {
            Pattern p = Pattern.compile(func + "\\(([^()]*)\\)");
            Matcher m = p.matcher(expr);

            while (m.find()) {
                String inside = m.group(1); // содержимое скобок
                Double valInside = evaluate(inside);
                if (valInside == null) return null;

                double fVal = switch (func) {
                    case "sin" -> Math.sin(valInside);
                    case "cos" -> Math.cos(valInside);
                    case "sqrt" -> (valInside < 0) ? Double.NaN : Math.sqrt(valInside);
                    default -> valInside;
                };

                // Заменяем "func(inside)" на fVal
                String fullMatch = m.group(0); // sin(...), cos(...), etc
                expr = expr.replace(fullMatch, Double.toString(fVal));
                // Чтобы корректно пересканировать строку, запускаем заново
                return resolveFunctions(expr);
            }
        }
        return expr;
    }

    private static String resolveParentheses(String expr) {
        if (expr == null) return null;
        while (expr.contains("(")) {
            int startIndex = expr.lastIndexOf("(");
            int endIndex = expr.indexOf(")", startIndex);
            if (endIndex < 0) {
                return null;
            }
            String inner = expr.substring(startIndex + 1, endIndex);
            Double val = evaluateNoParentheses(inner);
            if (val == null) return null;
            expr = expr.substring(0, startIndex) + val + expr.substring(endIndex + 1);
        }
        if (expr.contains(")")) {
            return null;
        }
        return expr;
    }

    private static Double evaluateNoParentheses(String expr) {
        if (expr == null) return null;
        var tokens = tokenize(expr);
        if (tokens.isEmpty()) return null;

        // Сначала обрабатываем * и /
        var processed = new java.util.ArrayList<String>();
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
                    if (val == 0) return null;
                    prevValue = (prevValue == null) ? val : (prevValue / val);
                    prevOp = null;
                } else {
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

        double result;
        try {
            result = Double.parseDouble(processed.get(0));
        } catch (Exception e) {
            return null;
        }
        for (int i = 1; i < processed.size(); i += 2) {
            String op = processed.get(i);
            double val = Double.parseDouble(processed.get(i + 1));
            switch (op) {
                case "+" -> result += val;
                case "-" -> result -= val;
            }
        }
        return result;
    }

    private static java.util.List<String> tokenize(String expr) {
        String[] tokens = expr.split("(?=[+\\-*/])|(?<=[+\\-*/])");
        var list = new java.util.ArrayList<String>();
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

    /**
     * Проверяем, что в выражении нет недопустимых символов (кроме переменных, цифр, операторов, скобок).
     * @param expr исходное выражение
     * @return true, если выражение выглядит потенциально валидным
     */
    public static boolean isExpressionValid(String expr) {
        // Разрешим [0-9], [a-zA-Z], + - * / . ( ) и пробелы
        // Проверим, что нет ничего вне этого набора
        return expr.matches("[0-9a-zA-Z+\\-*/().\\s]*");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите выражение:");
        String expr = sc.nextLine();

        if (!isExpressionValid(expr)) {
            System.out.println("Выражение содержит недопустимые символы!");
            return;
        }

        Double result = evaluateWithVars(expr);
        if (result == null || result.isNaN() || result.isInfinite()) {
            System.out.println("Ошибка вычисления!");
        } else {
            System.out.println("Результат: " + result);
        }
    }
}