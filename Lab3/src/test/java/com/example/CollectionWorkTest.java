package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Юнит-тесты для методов класса CollectionWork
 *  - Тестируем что методы возвращают ненулевое время и т.п
 */
public class CollectionWorkTest {

    @Test
    void testAddPerformanceNotZero() {
        long[] times = callTestAdd(100);
        // Тайм не нулевой
        assertTrue(times[0] > 0, "ArrayList add time should be > 0");
        assertTrue(times[1] > 0, "LinkedList add time should be > 0");
    }

    @Test
    void testRemovePerformanceNotZero() {
        long[] times = callTestRemove(100);
        assertTrue(times[0] > 0, "ArrayList remove time should be > 0");
        assertTrue(times[1] > 0, "LinkedList remove time should be > 0");
    }

    @Test
    void testGetPerformanceNotZero() {
        long[] times = callTestGet(100);
        assertTrue(times[0] > 0, "ArrayList get time should be > 0");
        assertTrue(times[1] > 0, "LinkedList get time should be > 0");
    }

    /**
     * Обёртки, чтобы добраться до приватных методов, чисто для примера
     * На практике можно сделать методы публичными или вынести в отдельный класс
     */
    private long[] callTestAdd(int iterations) {
        return ReflectionUtils.invokePrivateAddPerformance(iterations);
    }

    private long[] callTestRemove(int iterations) {
        return ReflectionUtils.invokePrivateRemovePerformance(iterations);
    }

    private long[] callTestGet(int iterations) {
        return ReflectionUtils.invokePrivateGetPerformance(iterations);
    }
}