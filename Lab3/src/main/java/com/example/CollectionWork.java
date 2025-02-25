package com.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Основной класс для сравнения производительности ArrayList и LinkedList
 */
public class CollectionWork {
    private static final int ITERATIONS = 1488;

    public static void main(String[] args) {
        System.out.println("Тест производительности: add");

        // Тесты
        testAddPerformance(ITERATIONS);
    }

    /**
     * Тестируем скорость добавления (add) для ArrayList и LinkedList
     * @param iterations количество элементов, которые добавляем
     */
    private static void testAddPerformance(int iterations) {
        // ArrayList
        List<Integer> arrayList = new ArrayList<>();
        long startArrayAdd = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            arrayList.add(i);
        }
        long endArrayAdd = System.nanoTime();
        long durationArrayAdd = endArrayAdd - startArrayAdd;

        // LinkedList
        List<Integer> linkedList = new LinkedList<>();
        long startLinkedAdd = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            linkedList.add(i);
        }
        long endLinkedAdd = System.nanoTime();
        long durationLinkedAdd = endLinkedAdd - startLinkedAdd;

        // Резы
        System.out.println("----- Add Performance -----");
        System.out.println("Iterations: " + iterations);
        System.out.println("ArrayList add time (ns):   " + durationArrayAdd);
        System.out.println("LinkedList add time (ns): " + durationLinkedAdd);

        // TODO: Добавить запись результатов в какую-то общую структуру или таблицу
        // UPD: Просто печать
    }
}