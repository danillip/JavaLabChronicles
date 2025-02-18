package org.example;

/**
 * Класс контейнера для хранения целых чисел реализованный с использованием динамического массива
 */
public class IntContainer {
    private int[] data;
    private int size;
    private static final int INITIAL_CAPACITY = 10;

    /**
     * Конструктор по умолчанию
     */
    public IntContainer() {
        data = new int[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Добавляет элемент в контейнер
     *
     * @param value значение элемента
     */
    public void add(int value) {
        ensureCapacity();
        data[size++] = value;
    }

    /**
     * Возвращает элемент по указанному индексу
     *
     * @param index индекс элемента
     * @return значение элемента
     * @throws IndexOutOfBoundsException если индекс недопустимый
     */
    public int get(int index) {
        checkIndex(index);
        return data[index];
    }

    /**
     * Удаляет элемент по указанному индексу и возвращает его
     *
     * @param index индекс элемента для удаления
     * @return удалённое значение
     * @throws IndexOutOfBoundsException если индекс недопустимый
     */
    public int remove(int index) {
        checkIndex(index);
        int removed = data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
        return removed;
    }

    /**
     * Возвращает текущее количество элементов в контейнере
     *
     * @return размер контейнера
     */
    public int size() {
        return size;
    }

    /**
     * Проверяет что индекс находится в допустимых пределах
     *
     * @param index проверяемый индекс
     * @throws IndexOutOfBoundsException если индекс недопустимый
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "Индекс " + index + " вне диапазона (0 - " + (size - 1) + ")"
            );
        }
    }

    /**
     * Расширяет массив при достижении его емкости
     */
    private void ensureCapacity() {
        if (size >= data.length) {
            int newCapacity = data.length * 2;
            int[] newData = new int[newCapacity];
            System.arraycopy(data, 0, newData, 0, data.length);
            data = newData;
        }
    }
}