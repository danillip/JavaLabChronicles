package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тестируем основные методы класса CollectionWork
 * Добавлены доп проверки
 */
public class CollectionWorkTest {

    @Test
    void testAddPerformanceNotZero() {
        long[] times = CollectionWork.testAddPerformance(100);
        assertTrue(times[0] > 0, "ArrayList add time should be > 0");
        assertTrue(times[1] > 0, "LinkedList add time should be > 0");
    }

    @Test
    void testRemovePerformanceNotZero() {
        long[] times = CollectionWork.testRemovePerformance(100);
        assertTrue(times[0] > 0, "ArrayList remove time should be > 0");
        assertTrue(times[1] > 0, "LinkedList remove time should be > 0");
    }

    @Test
    void testGetPerformanceNotZero() {
        long[] times = CollectionWork.testGetPerformance(100);
        assertTrue(times[0] > 0, "ArrayList get time should be > 0");
        assertTrue(times[1] > 0, "LinkedList get time should be > 0");
    }
}