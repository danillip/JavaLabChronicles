package com.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Основной класс для сравнения производительности ArrayList и LinkedList
 *
 *  - Добавлен метод testGetPerformance
 *  - Вызываем все три метода в main
 */
public class CollectionWork {
    private static final int ITERATIONS = 1000;

    public static void main(String[] args) {
        System.out.println("Тест производительности: add, remove, get");

        testAddPerformance(ITERATIONS);
        testRemovePerformance(ITERATIONS);
        testGetPerformance(ITERATIONS);
    }

    /**
     * Тестируем скорость добавления (add)
     */
    private static void testAddPerformance(int iterations) {
        List<Integer> arrayList = new ArrayList<>();
        long startArrayAdd = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            arrayList.add(i);
        }
        long endArrayAdd = System.nanoTime();
        long durationArrayAdd = endArrayAdd - startArrayAdd;

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
     * Удаляем с конца (size-1)
     */
    private static void testRemovePerformance(int iterations) {
        List<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < iterations; i++) {
            arrayList.add(i);
        }
        long startArrayRemove = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            arrayList.remove(arrayList.size() - 1);
        }
        long endArrayRemove = System.nanoTime();
        long durationArrayRemove = endArrayRemove - startArrayRemove;

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

    /**
     * Тестируем (get)
     * Будем в цикле обращаться к случайным индексам
     */
    private static void testGetPerformance(int iterations) {
        Random random = new Random();

        // Заполняем списки
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < iterations; i++) {
            arrayList.add(i);
            linkedList.add(i);
        }

        // ArrayList get
        long startArrayGet = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            int idx = random.nextInt(iterations);
            arrayList.get(idx);
        }
        long endArrayGet = System.nanoTime();
        long durationArrayGet = endArrayGet - startArrayGet;

        // LinkedList get
        long startLinkedGet = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            int idx = random.nextInt(iterations);
            linkedList.get(idx);
        }
        long endLinkedGet = System.nanoTime();
        long durationLinkedGet = endLinkedGet - startLinkedGet;

        System.out.println("----- Get Performance -----");
        System.out.println("Iterations: " + iterations);
        System.out.println("ArrayList get time (ns):   " + durationArrayGet);
        System.out.println("LinkedList get time (ns): " + durationLinkedGet);
    }
}