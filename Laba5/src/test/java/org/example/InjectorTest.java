package org.example;

import org.example.beans.SomeBean;
import org.example.injector.Injector;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Unit-тест для проверки инъекции и вывода в консоль
 * Демонстрирует перехват System.out
 */
public class InjectorTest {

    @Test
    public void testInjectionAndOutput() {
        // Подготовка: создаём SomeBean и инжектим
        SomeBean sb = new SomeBean();
        new Injector().inject(sb);

        // Перехватываем System.out
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;   // Сохраняем оригинальный поток

        try {
            System.setOut(new PrintStream(outContent));

            // Вызываем метод foo(), который внутри печатает что-то
            sb.foo();

        } finally {
            // Восстанавливаем System.out, чтобы не ломать другие тесты
            System.setOut(originalOut);
        }

        // Преобразуем перехваченный вывод в строку
        String consoleOutput = outContent.toString().trim();
        // Ожидается, что SomeImpl выведет A, а SODoer выведет C
        // trim() убирает лишние переносы строк, если что

        // Проверка, что выход содержит A и C в любом порядке
        // Но чаще всего ожидается именно "A\nC"

        Assert.assertTrue(
                "Вывод не содержит 'A'",
                consoleOutput.contains("A")
        );
        Assert.assertTrue(
                "Вывод не содержит 'C'",
                consoleOutput.contains("C")
        );
    }
}