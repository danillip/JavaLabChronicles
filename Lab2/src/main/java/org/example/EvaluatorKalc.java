package org.example;

/**
 * Основной класс для вычисления выражений
 * TODO: Парсер пока что возращает null - нужно доработать
 * UPD: Дальше будем добавлять аналитику строк и т.д
 */
public class EvaluatorKalc {

    /**
     * Временная заглушка для парсинга
     * @param expr математическое выражение
     * @return пока null
     */
    public static Object parseExpression(String expr) {
        // TODO: Реализовать разбор выражения
        return null;
    }

    public static void main(String[] args) {
        System.out.println("Hello from EvaluatorKalc!");
        // Пробуем вызвать парсер
        Object result = parseExpression("1+2");
        System.out.println("Парсер вернул: " + result);
    }
}
