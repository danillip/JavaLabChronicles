package org.example;

// Начальная версия класса IntContainer с полями и базовым методом add
// TODO: добавить методы get и remove, а также проверку индекса
public class IntContainer {
    // TODO: возможно, увеличить размер начального массива
    private int[] data = new int[10];
    private int size = 0;

    // Метод для добавления элемента (без проверки вместимости)
    public void add(int value) {
        data[size++] = value;
    }
}
