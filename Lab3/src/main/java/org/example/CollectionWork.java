package org.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Класс для сравнения производительности ArrayList и LinkedList
 *
 * <p>Методы тестируют три операции:
 *  1. add
 *  2. remove
 *  3. get
 * </p>
 *
 * <p>Результаты выводятся в консоль в виде простой таблицы</p>
 *
 * @author danillip
 * @version 1.0
 */
public class CollectionWork {

    /**
     * Количество итераций для теста по умолчанию
     */
    private static final int DEFAULT_ITERATIONS = 1488;

    /**
     * Точка входа в программу. Запускает тесты и выводит результаты
     *
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args) {
        int iterations = DEFAULT_ITERATIONS;
        long[] addTimes = testAddPerformance(iterations);
        long[] removeTimes = testRemovePerformance(iterations);
        long[] getTimes = testGetPerformance(iterations);

        System.out.println("\n=== Результаты ===");
        System.out.printf("%-10s | %-18s | %-18s | %-18s\n",
                "Operation", "Iterations", "ArrayList (ns)", "LinkedList (ns)");
        System.out.println("---------------------------------------------------------------");
        System.out.printf("%-10s | %-18d | %-18d | %-18d\n",
                "Add", iterations, addTimes[0], addTimes[1]);
        System.out.printf("%-10s | %-18d | %-18d | %-18d\n",
                "Remove", iterations, removeTimes[0], removeTimes[1]);
        System.out.printf("%-10s | %-18d | %-18d | %-18d\n",
                "Get", iterations, getTimes[0], getTimes[1]);
    }

    /**
     * Измеряем время выполнения операции add для ArrayList и LinkedList
     *
     * @param iterations количество элементов
     * @return массив из 2 элементов: [время ArrayList (ns), время LinkedList (ns)]
     */
    static long[] testAddPerformance(int iterations) {
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
     * Измеряем время выполнения операции remove для ArrayList и LinkedList (удаляем с конца)
     *
     * @param iterations количество элементов
     * @return массив из 2 элементов: [время ArrayList (ns), время LinkedList (ns)]
     */
    static long[] testRemovePerformance(int iterations) {
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
     * Измеряем время выполнения операции get (доступ к случайному индексу)
     * для ArrayList и LinkedList
     *
     * @param iterations количество элементов
     * @return массив из 2 элементов: [время ArrayList (ns), время LinkedList (ns)]
     */
    static long[] testGetPerformance(int iterations) {
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