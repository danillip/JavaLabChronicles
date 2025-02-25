package com.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Основной класс для сравнения производительности ArrayList и LinkedList
 *
 *  - Рефактор методов, возвращаем время выполнения + таблица
 */
public class CollectionWork {
    private static final int ITERATIONS = 1488;

    public static void main(String[] args) {
        long[] addTimes = testAddPerformance(ITERATIONS);
        long[] removeTimes = testRemovePerformance(ITERATIONS);
        long[] getTimes = testGetPerformance(ITERATIONS);

        System.out.println("\n=== Результаты ===");
        System.out.printf("%-10s | %-18s | %-18s | %-18s\n",
                "Operation", "Iterations", "ArrayList (ns)", "LinkedList (ns)");
        System.out.println("---------------------------------------------------------------");
        System.out.printf("%-10s | %-18d | %-18d | %-18d\n",
                "Add", ITERATIONS, addTimes[0], addTimes[1]);
        System.out.printf("%-10s | %-18d | %-18d | %-18d\n",
                "Remove", ITERATIONS, removeTimes[0], removeTimes[1]);
        System.out.printf("%-10s | %-18d | %-18d | %-18d\n",
                "Get", ITERATIONS, getTimes[0], getTimes[1]);
    }

    /**
     * Тестируем (add)
     * @return массив: [время ArrayList, время LinkedList]
     */
    private static long[] testAddPerformance(int iterations) {
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

        return new long[]{durationArrayAdd, durationLinkedAdd};
    }

    /**
     * Тестируем (remove) с конца
     * @return массив: [время ArrayList, время LinkedList]
     */
    private static long[] testRemovePerformance(int iterations) {
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

        return new long[]{durationArrayRemove, durationLinkedRemove};
    }

    /**
     * Тестируем (get) к случайным индексам
     * @return массив: [время ArrayList, время LinkedList]
     */
    private static long[] testGetPerformance(int iterations) {
        Random random = new Random();
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < iterations; i++) {
            arrayList.add(i);
            linkedList.add(i);
        }

        long startArrayGet = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            int idx = random.nextInt(iterations);
            arrayList.get(idx);
        }
        long endArrayGet = System.nanoTime();
        long durationArrayGet = endArrayGet - startArrayGet;

        long startLinkedGet = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            int idx = random.nextInt(iterations);
            linkedList.get(idx);
        }
        long endLinkedGet = System.nanoTime();
        long durationLinkedGet = endLinkedGet - startLinkedGet;

        return new long[]{durationArrayGet, durationLinkedGet};
    }
}