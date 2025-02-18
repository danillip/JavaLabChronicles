package org.example;

public class IntContainerTest {
    public static void main(String[] args) {
        IntContainer container = new IntContainer();

        // Добавление элементов
        System.out.println("Добавление элементов:");
        for (int i = 0; i < 5; i++) {
            container.add(i * 10);
            System.out.println("Добавлен: " + (i * 10));
        }

        // Получение элементов
        System.out.println("\nПолучение элементов:");
        for (int i = 0; i < 5; i++) {
            System.out.println("Элемент по индексу " + i + ": " + container.get(i));
        }

        // Удаление элементов
        System.out.println("\nУдаление элемента с индексом 2 (ожидается 20):");
        System.out.println("Удалён: " + container.remove(2));

        // Проверка после удаления
        System.out.println("\nСостояние после удаления:");
        for (int i = 0; i < 4; i++) { // после удаления размер уменьшился
            System.out.println("Элемент по индексу " + i + ": " + container.get(i));
        }
    }
}
