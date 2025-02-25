package com.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Основной класс для сравнения производительности ArrayList и LinkedList
 *
 * TODO:
 *  1. Реализовать методы для тестирования add, remove (delete), get
 *  2. Засечь время (System.nanoTime() или System.currentTimeMillis())
 *  3. Вывести результаты в таблицу
 *
 * UPD: Добавлены поля ITERATIONS и заготовка для тестовых методов
 */
public class CollectionWork {

    /**
     * Количество операций, которое будем выполнять
     * TODO: Позже сделать ввод этого параметра из аргументов или из настроек
     */
    private static final int ITERATIONS = 1000; // если вопрос про final то это чтобы неизменным он был после инициализации

    public static void main(String[] args) {
        // проверка запуска
        System.out.println("Коллекции — тест производительности.");

        // TODO: методы сравнения некст
    }

    /**
     * TODO: Метод для тестирования скорости добавления
     */
    private static void testAddPerformance(int iterations) {
        // UPD: Пока только заготовка
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();
        // позже
    }
}
