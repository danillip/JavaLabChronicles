package org.example;

/**
 * Начальная версия контейнера для хранения целых чисел
 * TODO: Реализовать методы get и remove, добавить проверку индекса
 */
public class IntContainer {
    private int[] data = new int[10];
    private int size = 0;

    public void add(int value) {
        data[size++] = value;
    }

    // Скелет метода get
    public int get(int index) {
        // TODO: добавить проверку индекса
        return data[index];
    }

    // Скелет метода remove
    public int remove(int index) {
        // TODO: добавить проверку индекса и сдвиг элементов
        int removed = data[index];
        // Простой сдвиг элементов влево
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
        return removed;
    }
}
