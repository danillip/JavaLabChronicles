package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * JUnit-тесты для класса CSVReaderUtil
 */
public class CSVReaderUtilTest {

    @Test
    public void testReadPeopleFromCSV() throws Exception {
        List<Person> people = CSVReaderUtil.readPeopleFromCSV("foreign_names.csv");
        // Для короткого файла можно проверить конкретные индексы
        // или хотя бы проверить что список не пуст
        Assertions.assertFalse(people.isEmpty(), "Список не должен быть пустым");

        // Проверим первого человека если файл не меняется
        Person first = people.get(0);
        Assertions.assertEquals(28281, first.getId());
        Assertions.assertEquals("Aahan", first.getName());
        Assertions.assertEquals("Male", first.getGender());
    }
}