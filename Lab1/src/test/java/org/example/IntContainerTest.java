package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit-тесты для класса IntContainer
 */
public class IntContainerTest {

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
        assertEquals(2, container.size());
        assertEquals(1, container.get(0));
        assertEquals(3, container.get(1));
    }

    @Test
    public void testIndexOutOfBounds() {
        IntContainer container = new IntContainer();
        container.add(100);
        assertThrows(IndexOutOfBoundsException.class, () -> container.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> container.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> container.remove(1));
    }
}
