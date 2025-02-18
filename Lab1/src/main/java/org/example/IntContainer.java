package org.example;

/**
 * Контейнер для хранения целых чисел
 * TODO: протестировать расширение массива
 */
public class IntContainer {
    private int[] data = new int[10];
    private int size = 0;

    public void add(int value) {
        ensureCapacity();  // UPD: проверяем вместимость массива
        data[size++] = value;
    }

    public int get(int index) {
        checkIndex(index);
        return data[index];
    }

    public int remove(int index) {
        checkIndex(index);
        int removed = data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
        return removed;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс " + index + " вне диапазона (0 - " + (size - 1) + ")");
        }
    }

    // Метод для расширения массива при достижении его емкости
    private void ensureCapacity() {
        if (size >= data.length) {
            int newCapacity = data.length * 2; // UPD: удваиваем размер массива
            int[] newData = new int[newCapacity];
            System.arraycopy(data, 0, newData, 0, data.length);
            data = newData;
        }
    }
}
