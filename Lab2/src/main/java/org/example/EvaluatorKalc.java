package org.example;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * EvaluatorKalc - программа для вычисления математических выражений,
 * содержащих числа, переменные, функции, скобки и операторы +, -, *, /.
 *
 * Особенности:
 * 1. Поддержка переменных (напр. x, y, varName), значения запрашиваются у пользователя один раз.
 * 2. Поддержка функций sin(...), cos(...), sqrt(...).
 * 3. Проверка корректности выражения (примитивная).
 * 4. Выбрасывает ошибку (или возвращает null), если некорректно расставлены скобки или деление на 0
 *
 * @author danillip
 * @version 1.0
 */
public class EvaluatorKalc {

    // Хранилище значений переменных
    private static final Map<String, Double> variables = new HashMap<>();

    /**
     * Точка входа в программу.
     * Запрашивает у пользователя выражение, проверяет его
     * и вычисляет (учитывая переменные и функции)
     *
     * @param args не используется
     */
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

    /**
     * Проверяет что в выражении нет недопустимых символов (кроме:
     * цифр, переменных [a-zA-Z], операторов +, -, *, /, точки,
     * скобок и пробелов).
     *
     * @param expr исходное выражение
     * @return true, если выражение выглядит потенциально валидным
     */
    public static boolean isExpressionValid(String expr) {
        // Разрешим [0-9], [a-zA-Z], + - * / . ( ) и пробелы
        return expr.matches("[0-9a-zA-Z+\\-*/().\\s]*");
    }

    /**
     * Полная функция вычисления выражения с учётом переменных.
     * Сначала запрашивает у пользователя значения переменных, если они есть
     * затем подменяет их в тексте выражения и вызывает evaluate (без переменных)
     *
     * @param expr строка с математическим выражением
     * @return результат вычисления или null при ошибке
     */
    public static Double evaluateWithVars(String expr) {
        requestVariables(expr);
        String replaced = substituteVariables(expr);
        return evaluate(replaced);
    }

    /**
     * Просматривает выражение и ищет имена переменных (a-zA-Z),
     * если переменная ещё не была инициализирована - запрашивает у пользователя.
     *
     * @param expr исходное выражение
     */
    public static void requestVariables(String expr) {
        Scanner sc = new Scanner(System.in);
        Matcher matcher = Pattern.compile("\\b([a-zA-Z]+)\\b").matcher(expr);
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
     * Подменяет имена переменных на их числовые значения в выражении
     *
     * @param expr исходное выражение
     * @return выражение, где переменные заменены на числа
     */
    public static String substituteVariables(String expr) {
        for (Map.Entry<String, Double> e : variables.entrySet()) {
            expr = expr.replaceAll("\\b" + e.getKey() + "\\b", e.getValue().toString());
        }
        return expr;
    }

    /**
     * Основной метод вычисления выражения БЕЗ переменных.
     * Поддерживает скобки, функции, приоритет операторов.
     *
     * @param expr выражение
     * @return результат или null при ошибке
     */
    public static Double evaluate(String expr) {
        // Сначала обрабатываем функции (sin, cos, sqrt и т.д.)
        expr = resolveFunctions(expr);
        // Затем обрабатываем скобки (рекурсивно)
        expr = resolveParentheses(expr);
        // Затем вычисляем итог
        return evaluateNoParentheses(expr);
    }

    /**
     * Находит вызовы функций вида sin(...), cos(...), sqrt(...)
     * вычисляет их рекурсивно (т.к. внутри могут быть скобки, другие функции)
     *
     * @param expr исходное выражение
     * @return выражение, где вызовы функций заменены на вычисленные значения
     */
    private static String resolveFunctions(String expr) {
        if (expr == null) return null;
        // Простой список поддерживаемых функций
        String[] funcs = {"sin", "cos", "sqrt"};
        for (String func : funcs) {
            Pattern p = Pattern.compile(func + "\\(([^()]*)\\)");
            Matcher m = p.matcher(expr);

            while (m.find()) {
                String inside = m.group(1); // содержимое скобок
                Double valInside = evaluate(inside); // рекурсивно
                if (valInside == null) return null;

                double fVal = switch (func) {
                    case "sin" -> Math.sin(valInside);
                    case "cos" -> Math.cos(valInside);
                    case "sqrt" -> (valInside < 0) ? Double.NaN : Math.sqrt(valInside);
                    default -> valInside;
                };

                String fullMatch = m.group(0); // например sin(...)
                expr = expr.replace(fullMatch, Double.toString(fVal));
                // Перезапуск чтобы найти повторные функции
                return resolveFunctions(expr);
            }
        }
        return expr;
    }

    /**
     * Ищет самые глубокие скобки, вычисляет их содержимое и заменяет в исходном выражении
     * Повторяет пока скобок не останется. Если скобки расставлены некорректно вернёт null
     *
     * @param expr выражение
     * @return упрощённое выражение без скобок
     */
    private static String resolveParentheses(String expr) {
        if (expr == null) return null;
        while (expr.contains("(")) {
            int startIndex = expr.lastIndexOf("(");
            int endIndex = expr.indexOf(")", startIndex);
            if (endIndex < 0) {
                // Нет закрывающей скобки
                return null;
            }
            String inner = expr.substring(startIndex + 1, endIndex);
            Double val = evaluateNoParentheses(inner);
            if (val == null) return null;
            expr = expr.substring(0, startIndex) + val + expr.substring(endIndex + 1);
        }
        // Если осталась незакрытая ')', значит ошибка
        if (expr.contains(")")) {
            return null;
        }
        return expr;
    }

    /**
     * Вычисляет выражение, в котором нет скобок и функций. Считаем, что остались
     * только числа, операторы +, -, *, /
     *
     * @param expr выражение без скобок и функций
     * @return числовой результат или null
     */
    private static Double evaluateNoParentheses(String expr) {
        if (expr == null) return null;
        List<String> tokens = tokenize(expr);
        if (tokens.isEmpty()) return null;

        // Сначала * и /
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

        // Теперь в processed остались числа и операторы +/-
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

    /**
     * Разбивает строку выражения на токены (числа и операторы)
     *
     * @param expr выражение
     * @return список токенов
     */
    private static List<String> tokenize(String expr) {
        // Разбиваем по операторам + - * /
        String[] arr = expr.split("(?=[+\\-*/])|(?<=[+\\-*/])");
        List<String> tokens = new ArrayList<>();
        for (String token : arr) {
            token = token.trim();
            if (!token.isEmpty()) {
                tokens.add(token);
            }
        }
        return tokens;
    }

    /**
     * Проверяет, является ли токен оператором (+, -, *, /)
     *
     * @param token токен
     * @return true, если оператор
     */
    private static boolean isOperator(String token) {
        return "+".equals(token) || "-".equals(token)
                || "*".equals(token) || "/".equals(token);
    }
}