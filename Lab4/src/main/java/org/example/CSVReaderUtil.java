package org.example;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Утилитный класс для чтения данных о людях из CSV-файла и формирования списка {@link Person}
 * <p>
 * По умолчанию файл <b>foreign_names.csv</b> должен находиться в папке ресурсов (resources)
 * </p>
 * Схема CSV:
 * <pre>
 *     id;name;gender;BirtDate;Division;Salary
 * </pre>
 *
 * @author
 *     danillip
 * @version
 *     1.0
 */
public class CSVReaderUtil {

    /**
     * Разделитель (по условию ';').
     */
    private static final char DEFAULT_SEPARATOR = ';';

    /**
     * Формат даты в CSV-файле (dd.MM.yyyy).
     */
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    /**
     * Кэш уже созданных подразделений, чтобы не создавать дубликаты.
     */
    private static final Map<String, Division> divisionMap = new HashMap<>();

    /**
     * Считывает CSV-файл из ресурсов и возвращает список {@link Person}.
     * Формат CSV:
     * <pre>
     *     id;name;gender;BirtDate;Division;Salary
     * </pre>
     *
     * @param csvFilePath путь к CSV-файлу (относительно папки ресурсов)
     * @return список объектов {@link Person}
     * @throws Exception при ошибке чтения файла (например, отсутствует или неверные данные)
     */
    public static List<Person> readPeopleFromCSV(String csvFilePath) throws Exception {
        List<Person> people = new ArrayList<>();

        // Открываем InputStream на файл внутри ресурсов
        try (InputStream in = CSVReaderUtil.class.getClassLoader().getResourceAsStream(csvFilePath)) {

            if (in == null) {
                throw new FileNotFoundException("Файл не найден (ПЕРЕКИНУТЬ В РЕСУРСЫ): " + csvFilePath);
            }

            // Создаём парсер, указывая разделитель
            var csvParser = new CSVParserBuilder()
                    .withSeparator(DEFAULT_SEPARATOR)
                    .build();

            // Create CSVReader через CSVReaderBuilder
            CSVReader reader = new CSVReaderBuilder(new InputStreamReader(in))
                    .withCSVParser(csvParser)
                    .build();

            // skip заголовка
            reader.readNext();

            // read string
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                // nextLine: [id, name, gender, BirtDate, Division, Salary]
                if (nextLine.length < 6) {
                    // Если в строке меньше 6 столбцов – skip
                    continue;
                }

                long id = Long.parseLong(nextLine[0]);
                String name = nextLine[1];
                String gender = nextLine[2];
                LocalDate birthDate = LocalDate.parse(nextLine[3], DATE_FORMATTER);
                String divisionName = nextLine[4];
                double salary = Double.parseDouble(nextLine[5]);

                // Division – проверяем есть ли уже
                Division division = divisionMap.get(divisionName);
                if (division == null) {
                    division = new Division(divisionName);
                    divisionMap.put(divisionName, division);
                }

                Person person = new Person(id, name, gender, birthDate, division, salary);
                people.add(person);
            }

        }
        return people;
    }
}