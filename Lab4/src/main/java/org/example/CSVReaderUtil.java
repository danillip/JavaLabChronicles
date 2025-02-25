package org.example;

import com.opencsv.CSVReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Утилитный класс для чтения данных о людях из CSV
 */
public class CSVReaderUtil {

    private static final char DEFAULT_SEPARATOR = ';';

    /**
     * Считывает CSV файл из ресурсов и возвращает список Person
     * @param csvFilePath путь к CSV файлу (внутри ресурсов)
     * @return список объектов Person
     * @throws Exception при ошибке чтения файла
     */
    public static List<Person> readPeopleFromCSV(String csvFilePath) throws Exception {
        List<Person> people = new ArrayList<>();

        // TODO: сделать нормальный парсинг строчек и создание Person
        try (InputStream in = CSVReaderUtil.class.getClassLoader().getResourceAsStream(csvFilePath);
             CSVReader reader = in == null ? null : new CSVReader(new InputStreamReader(in), DEFAULT_SEPARATOR)) {

            if (reader == null) {
                throw new FileNotFoundException("Файл не найден: " + csvFilePath);
            }

            String[] nextLine;
            // Пропустим заголовок если нужно (readNext() чтобы пропустить первую строку)
            reader.readNext();

            while ((nextLine = reader.readNext()) != null) {
                // Дебаг вывод в консоль массива
                System.out.println("DEBUG CSV LINE: " + String.join(", ", nextLine));

                // TODO: парсить массив nextLine, создавать Person, добавлять в people
            }

        }
        return people;
    }
}