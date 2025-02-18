package org.example;

/**
 * Контейнер для хранения целых чисел.
 * TODO: протестировать работу методов.
 */
public class IntContainer {
    private int[] data = new int[10];
    private int size = 0;

    public void add(int value) {
        data[size++] = value;
    }

    public int get(int index) {
        checkIndex(index);  // Добавляем проверку индекса
        return data[index];
    }

    public int remove(int index) {
        checkIndex(index);  // Добавляем проверку индекса
        int removed = data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
        return removed;
    }

    // Новый метод для проверки корректности индекса
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс " + index + " вне диапазона (0 - " + (size - 1) + ")");
        }
    }
}
