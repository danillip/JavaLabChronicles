package org.example;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Главный класс с методом main, который читает CSV и выводит результат
 */
public class CollectionWorkWithFile {

    // Сколько строк выводить
    private static final int MAX_PRINT = 10;

    public static void main(String[] args) {
        try {
            List<Person> people = CSVReaderUtil.readPeopleFromCSV("foreign_names.csv");
            System.out.println("Считано людей: " + people.size());

            // Выводим только первые MAX_PRINT строк
            for (int i = 0; i < people.size(); i++) {
                if (i >= MAX_PRINT) {
                    System.out.println("... Показаны только первые " + MAX_PRINT + " записей ...");
                    break;
                }
                System.out.println(people.get(i));
            }

            // Считаем статистику по зарплатам
            DoubleSummaryStatistics salaryStats = people.stream()
                    .collect(Collectors.summarizingDouble(Person::getSalary));

            System.out.println("\nСтатистика по зарплатам:");
            System.out.println("Минимальная: " + salaryStats.getMin());
            System.out.println("Максимальная: " + salaryStats.getMax());
            System.out.println("Средняя: " + salaryStats.getAverage());

        } catch (Exception e) {
            System.err.println("Ошибка при чтении CSV: " + e.getMessage());
            e.printStackTrace();
        }
    }
}