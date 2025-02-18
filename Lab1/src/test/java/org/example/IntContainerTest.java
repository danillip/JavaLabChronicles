package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit-тесты для класса IntContainer
 */
public class IntContainerTest {

    /**
     * Тестирование добавления и получения элементов в контейнере
     */
    @Test
    public void testAddAndGet() {
        IntContainer container = new IntContainer();
        container.add(5);
        container.add(10);
        assertEquals(5, container.get(0)); // Проверка что первый элемент равен 5
        assertEquals(10, container.get(1)); // Проверка что второй элемент равен 10
    }

    /**
     * Тестирование удаления элемента из контейнера
     */
    @Test
    public void testRemove() {
        IntContainer container = new IntContainer();
        container.add(1);
        container.add(2);
        container.add(3);
        int removed = container.remove(1); // Ждемс удаление элемента с индексом 1
        assertEquals(2, removed); // Проверка что удалён элемент со значением 2
        assertEquals(2, container.size()); // Проверка что размер контейнера после удаления — 2
        assertEquals(1, container.get(0)); // Проверка что первый элемент — 1
        assertEquals(3, container.get(1)); // Проверка что второй элемент — 3
    }

    /**
     * Тестирование на выброс исключения при неверных индексах
     */
    @Test
    public void testIndexOutOfBounds() {
        IntContainer container = new IntContainer();
        container.add(100);
        assertThrows(IndexOutOfBoundsException.class, () -> container.get(-1)); // Проверка на отрицательный индекс
        assertThrows(IndexOutOfBoundsException.class, () -> container.get(1)); // Проверка индекс который выходит за пределы
        assertThrows(IndexOutOfBoundsException.class, () -> container.remove(1)); // Проверка на удаление по неверному индексу
    }
}
