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
 * Утилитный класс для чтения данных о людях из CSV
 */
public class CSVReaderUtil {

    private static final char DEFAULT_SEPARATOR = ';';
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    // Храним уже созданные подразделения в мапе чтобы не плодить кучу одинаковых Division
    private static final Map<String, Division> divisionMap = new HashMap<>();

    /**
     * Считывает CSV файл из ресурсов и возвращает список Person
     * Формат CSV:
     * id;name;gender;BirtDate;Division;Salary
     *
     * @param csvFilePath путь к CSV файлу (внутри ресурсов)
     * @return список объектов Person
     * @throws Exception при ошибке чтения файла
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