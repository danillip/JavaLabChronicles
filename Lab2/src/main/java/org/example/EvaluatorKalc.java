package org.example;

/**
 * UPD: Добавили простейший парсинг: разделение строки по знакам + и -
 */
public class EvaluatorKalc {

    /**
     * Разбирает выражение, пока только учитываем знаки + и -
     * TODO: добавить обработку умножения, деления, скобок
     * @param expr математическое выражение
     * @return черновой результат (как строка для наглядности)
     */
    public static String parseExpression(String expr) {
        // Черновая реализация
        // Допустим, разобьем строку по + и - и выведем частички
        String[] tokens = expr.split("(?=[+-])|(?<=[+-])");
        StringBuilder sb = new StringBuilder("Tokens: ");
        for (String t : tokens) {
            sb.append("[").append(t).append("] ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("=== EvaluatorKalc v0.1 ===");
        String testExpr = "1+2-3+10";
        String result = parseExpression(testExpr);
        System.out.println("Исходное выражение: " + testExpr);
        System.out.println("Результат парсинга: " + result);
    }
}
