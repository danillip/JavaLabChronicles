package com.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Основной класс для сравнения производительности ArrayList и LinkedList
 *
 *  - Добавлен метод testRemovePerformance
 *  - Добавлена вызовы методов в main
 */
public class CollectionWork {
    private static final int ITERATIONS = 1000;

    public static void main(String[] args) {
        System.out.println("Тест производительности: add, remove");

        testAddPerformance(ITERATIONS);
        testRemovePerformance(ITERATIONS);
    }

    /**
     * Тест добавления
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

        System.out.println("----- Add Performance -----");
        System.out.println("Iterations: " + iterations);
        System.out.println("ArrayList add time (ns):   " + durationArrayAdd);
        System.out.println("LinkedList add time (ns): " + durationLinkedAdd);
    }

    /**
     * Тестируем (remove)
     * Удаляем из конца списка для упрощения
     */
    private static void testRemovePerformance(int iterations) {
        // ArrayList
        List<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < iterations; i++) {
            arrayList.add(i);
        }
        long startArrayRemove = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            // Ремув с конца
            arrayList.remove(arrayList.size() - 1);
        }
        long endArrayRemove = System.nanoTime();
        long durationArrayRemove = endArrayRemove - startArrayRemove;

        // LinkedList
        List<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < iterations; i++) {
            linkedList.add(i);
        }
        long startLinkedRemove = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            linkedList.remove(linkedList.size() - 1);
        }
        long endLinkedRemove = System.nanoTime();
        long durationLinkedRemove = endLinkedRemove - startLinkedRemove;

        System.out.println("----- Remove Performance -----");
        System.out.println("Iterations: " + iterations);
        System.out.println("ArrayList remove time (ns):   " + durationArrayRemove);
        System.out.println("LinkedList remove time (ns): " + durationLinkedRemove);
    }
}