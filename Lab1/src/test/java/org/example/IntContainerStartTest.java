package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Начальные unit-тесты для класса IntContainer
 */
public class IntContainerStartTest {

    @Test
    public void testAddAndGet() {
        IntContainer container = new IntContainer();
        container.add(5);
        container.add(10);
        assertEquals(5, container.get(0));
        assertEquals(10, container.get(1));
    }

    @Test
    public void testRemove() {
        IntContainer container = new IntContainer();
        container.add(1);
        container.add(2);
        container.add(3);
        int removed = container.remove(1);
        assertEquals(2, removed);
        // Проверяем сдвиг элементов после удаления
        assertEquals(1, container.get(0));
        assertEquals(3, container.get(1));
        // Проверяем что размер уменьшился
        assertEquals(2, container.size());
    }
}
