package org.example;

/**
 * Тестим работу программы
 */
public class IntContainerStartTest {

    /**
     * В этом методе выполняются операции добавления, получения и удаления элементов
     * а также проверка состояния контейнера после удаления элемента.
     *
     * @param args аргументы ком строки (не используются в этом тесте)
     */
    public static void main(String[] args) {
        // Создание нового контейнера для целых чисел
        IntContainer container = new IntContainer();

        // Добавление элементов
        System.out.println("Добавление элементов:");
        // Добавляем 5 элементов в контейнер (0, 10, 20, 30, 40)
        for (int i = 0; i < 5; i++) {
            container.add(i * 10);  // Тоже добавление
            System.out.println("Добавлен: " + (i * 10));
        }

        System.out.println("\nПолучение элементов:");
        // Получаем и выводим все добавленные элементы
        for (int i = 0; i < 5; i++) {
            System.out.println("Элемент по индексу " + i + ": " + container.get(i));
        }

        System.out.println("\nУдаление элемента с индексом 2 (ожидается 20):");
        System.out.println("Удалён: " + container.remove(2));

        System.out.println("\nСостояние после удаления:");
        // Проверяем элементы после удаления (размер уменьшился на 1)
        for (int i = 0; i < 4; i++) {
            System.out.println("Элемент по индексу " + i + ": " + container.get(i));
        }
    }
}
