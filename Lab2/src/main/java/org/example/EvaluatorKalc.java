package org.example;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * EvaluatorKalc - программа для вычисления математических выражений,
 * содержащих числа, переменные, функции, скобки и операторы +, -, *, /
 *
 * Особенности:
 * 1. Поддержка переменных (например, x, y, varName), значения запрашиваются у пользователя один раз
 * 2. Поддержка функций sin(...), cos(...), sqrt(...)
 * 3. Проверка корректности выражения (примитивная)
 * 4. Возвращает null (и выводит "Ошибка вычисления!" с деталями), если:
 *    - некорректно расставлены скобки
 *    - деление на 0
 *    - нечисловой токен и т.п
 *
 * @author danillip
 * @version 1.0
 */
public class EvaluatorKalc {

    // Хранилище значений переменных
    private static final Map<String, Double> variables = new HashMap<>();
    // Список слов которые не считаем переменными
    private static final Set<String> FUNCTION_NAMES = Set.of("sin", "cos", "sqrt");

    // Текст последней ошибки
    private static String lastError = null;

    /**
     * Точка входа в программу.
     * Запрашивает у пользователя выражение, проверяет его
     * и вычисляет (учитывая переменные и функции)
     *
     * @param args не используется
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Примеры выражений:");
        System.out.println("  (2+3)*(5-1)");
        System.out.println("  sin(0)+cos(0)+sqrt(9)");
        System.out.println("  x + sin(y)*3, где x,y -- переменные (будет запрос)");
        System.out.println("Поддерживаемые функции: sin(...), cos(...), sqrt(...)");
        System.out.println("---------------------------------------");
        System.out.println("Введите выражение:");

        String expr = sc.nextLine();

        // Сначала проверим, нет ли недопустимых символов
        if (!isExpressionValid(expr)) {
            System.out.println("Выражение содержит недопустимые символы!\n" +
                    "Разрешены только цифры, буквы [a-zA-Z], знаки + - * /, скобки ( ), точка для десятичных и пробелы.");
            return;
        }

        Double result = evaluateWithVars(expr);
        if (result == null || result.isNaN() || result.isInfinite()) {
            System.out.println("Ошибка вычисления!");
            if (lastError != null && !lastError.isBlank()) {
                // Показать дополнительную информацию об ошибке
                System.out.println("Причина: " + lastError);
            }
        } else {
            System.out.println("Результат: " + result);
        }
    }

    /**
     * Проверяет, что в выражении нет недопустимых символов (кроме:
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
     * Сначала запрашивает у пользователя значения переменных (кроме известных функций),
     * затем подменяет их в тексте выражения и вызывает evaluate (без переменных).
     *
     * @param expr строка с математическим выражением
     * @return результат вычисления или null при ошибке
     */
    public static Double evaluateWithVars(String expr) {
        lastError = null; // сбрасываем ошибку
        requestVariables(expr);
        String replaced = substituteVariables(expr);
        return evaluate(replaced);
    }

    /**
     * Ищет имена переменных (слова из [a-zA-Z]+) в выражении, но пропускает
     * уже известные функции (sin, cos, sqrt). Если переменная ещё не
     * была инициализирована - запрашивает у пользователя.
     *
     * @param expr исходное выражение
     */
    public static void requestVariables(String expr) {
        Scanner sc = new Scanner(System.in);
        Matcher matcher = Pattern.compile("\\b([a-zA-Z]+)\\b").matcher(expr);
        while (matcher.find()) {
            String var = matcher.group(1);
            // Проверяем, не является ли оно именем функции
            if (FUNCTION_NAMES.contains(var.toLowerCase())) {
                continue; // Пропускаем, это функция, а не переменная
            }
            // Если переменная ещё не инициализирована - спрашиваем
            if (!variables.containsKey(var)) {
                System.out.print("Введите значение для переменной " + var + ": ");
                try {
                    double val = sc.nextDouble();
                    variables.put(var, val);
                } catch (InputMismatchException e) {
                    // Если пользователь ввёл не число
                    lastError = "Значение переменной '" + var + "' должно быть числом.";
                    // Принудительно завершим, так как дальше вычислять бессмысленно
                    return;
                }
            }
        }
    }

    /**
     * Подменяет имена переменных на их числовые значения в выражении.
     *
     * @param expr исходное выражение
     * @return выражение, где переменные заменены на числа
     */
    public static String substituteVariables(String expr) {
        for (Map.Entry<String, Double> e : variables.entrySet()) {
            // Заменяем все вхождения переменной (var) на её значение.
            expr = expr.replaceAll("\\b" + e.getKey() + "\\b", e.getValue().toString());
        }
        return expr;
    }

    /**
     * Основной метод вычисления выражения БЕЗ переменных.
     * Поддерживает скобки, функции, приоритет операторов.
     *
     * @param expr выражение (уже без переменных)
     * @return результат или null при ошибке
     */
    public static Double evaluate(String expr) {
        if (expr == null) {
            if (lastError == null) {
                lastError = "Пустое выражение или некорректные данные.";
            }
            return null;
        }

        // Сначала обрабатываем функции (sin, cos, sqrt и т.д)
        expr = resolveFunctions(expr);
        if (expr == null) {
            // Ошибка была выставлена в resolveFunctions
            return null;
        }

        // Затем обрабатываем скобки (рекурсивно)
        expr = resolveParentheses(expr);
        if (expr == null) {
            // Ошибка была выставлена в resolveParentheses
            return null;
        }

        // Затем вычисляем итог
        return evaluateNoParentheses(expr);
    }

    /**
     * Находит вызовы функций вида sin(...), cos(...), sqrt(...),
     * вычисляет их рекурсивно (т.к. внутри могут быть вложенные выражения).
     *
     * @param expr исходное выражение
     * @return выражение, где вызовы функций заменены на вычисленные значения
     */
    private static String resolveFunctions(String expr) {
        // Простой список поддерживаемых функций
        String[] funcs = {"sin", "cos", "sqrt"};
        for (String func : funcs) {
            Pattern p = Pattern.compile(func + "\\(([^()]*)\\)");
            Matcher m = p.matcher(expr);

            while (m.find()) {
                // Содержимое скобок
                String inside = m.group(1);
                // Рекурсивно вычисляем содержимое
                Double valInside = evaluate(inside);
                if (valInside == null) {
                    if (lastError == null) {
                        lastError = "Не удалось вычислить аргумент функции " + func + "(...)";
                    }
                    return null;
                }

                // Применяем саму функцию
                double fVal = switch (func) {
                    case "sin" -> Math.sin(valInside);
                    case "cos" -> Math.cos(valInside);
                    case "sqrt" -> {
                        if (valInside < 0) {
                            yield Double.NaN;
                        } else {
                            yield Math.sqrt(valInside);
                        }
                    }
                    default -> valInside;
                };

                // Заменяем "func(inside)" на полученное число
                String fullMatch = m.group(0); // например sin(...)
                expr = expr.replace(fullMatch, Double.toString(fVal));

                // Перезапуск чтобы найти повторные функции если они появились после подстановки
                return resolveFunctions(expr);
            }
        }
        return expr;
    }

    /**
     * Ищет самые глубокие скобки, вычисляет их содержимое и заменяет в исходном выражении.
     * Повторяет, пока не останется ни одной скобки.
     * Если скобки расставлены некорректно, вернёт null
     *
     * @param expr выражение
     * @return упрощённое выражение без скобок
     */
    private static String resolveParentheses(String expr) {
        while (expr.contains("(")) {
            int startIndex = expr.lastIndexOf("(");
            int endIndex = expr.indexOf(")", startIndex);
            if (endIndex < 0) {
                lastError = "Отсутствует закрывающая скобка ')'.";
                return null;
            }
            String inner = expr.substring(startIndex + 1, endIndex);
            Double val = evaluateNoParentheses(inner);
            if (val == null) {
                // Описание ошибки уже выставлено в evaluateNoParentheses
                return null;
            }

            // Заменяем ( ... ) на вычисленный результат
            expr = expr.substring(0, startIndex) + val + expr.substring(endIndex + 1);
        }

        // Если осталась незакрытая ')', значит ошибка
        if (expr.contains(")")) {
            lastError = "Присутствует лишняя закрывающая скобка ')'.";
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
        if (expr == null || expr.isBlank()) {
            if (lastError == null) {
                lastError = "Пустое выражение.";
            }
            return null;
        }

        List<String> tokens = tokenize(expr);
        if (tokens.isEmpty()) {
            lastError = "Не удалось выделить токены из выражения.";
            return null;
        }

        // Сначала обрабатываем * и /
        List<String> processed = new ArrayList<>();
        Double prevValue = null;
        String prevOp = null;

        for (String token : tokens) {
            if (isOperator(token)) {
                // Если это оператор (+, -, *, /) сохраняем его
                prevOp = token;
            } else {
                // Иначе это число (или не число)
                double val;
                try {
                    val = Double.parseDouble(token);
                } catch (Exception e) {
                    lastError = "Токен '" + token + "' не является числом.";
                    return null;
                }
                if (prevOp == null) {
                    // Это самое первое число
                    prevValue = val;
                } else if (prevOp.equals("*")) {
                    prevValue = (prevValue == null) ? val : (prevValue * val);
                    prevOp = null;
                } else if (prevOp.equals("/")) {
                    if (val == 0) {
                        lastError = "Деление на ноль.";
                        return null;
                    }
                    prevValue = (prevValue == null) ? val : (prevValue / val);
                    prevOp = null;
                } else {
                    // Если + или - то нужно закрыть предыдущий этап и перейти к следующему
                    processed.add(String.valueOf(prevValue));
                    processed.add(prevOp);
                    prevValue = val;
                    prevOp = null;
                }
            }
        }
        // Добавляем последнее накопленное значение
        if (prevValue != null) {
            processed.add(String.valueOf(prevValue));
        }

        // Теперь в processed остались числа и операторы +/- (в порядке слева направо)
        if (processed.isEmpty()) {
            lastError = "Нет числовых значений для вычисления.";
            return null;
        }

        double result;
        try {
            result = Double.parseDouble(processed.get(0));
        } catch (Exception e) {
            lastError = "Ошибка преобразования '" + processed.get(0) + "' в число.";
            return null;
        }

        for (int i = 1; i < processed.size(); i += 2) {
            String op = processed.get(i);
            double val;
            try {
                val = Double.parseDouble(processed.get(i + 1));
            } catch (Exception e) {
                lastError = "Ошибка преобразования '" + processed.get(i + 1) + "' в число.";
                return null;
            }
            switch (op) {
                case "+" -> result += val;
                case "-" -> result -= val;
                default -> {
                    lastError = "Неизвестный оператор: " + op;
                    return null;
                }
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
     * Проверяет является ли токен оператором (+, -, *, /)
     *
     * @param token токен
     * @return true, если оператор
     */
    private static boolean isOperator(String token) {
        return "+".equals(token) || "-".equals(token)
                || "*".equals(token) || "/".equals(token);
    }
}