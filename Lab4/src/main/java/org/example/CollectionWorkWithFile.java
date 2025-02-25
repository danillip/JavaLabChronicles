package org.example;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс CollectionWorkWithFile представляет основную точку входа в приложение.
 * <p>
 * Он читает данные о людях из CSV-файла (foreign_names.csv), выводит ограниченное
 * количество записей, а также рассчитывает и отображает статистику по зарплатам.
 * </p>
 *
 * Особенности:
 * <ul>
 *     <li>Чтение CSV с помощью библиотеки OpenCSV</li>
 *     <li>Использование коллекций для хранения и обработки данных</li>
 *     <li>Анализ набора данных (минимальная, максимальная и средняя зарплата)</li>
 *     <li>Ограничение числа выводимых строк с помощью константы MAX_PRINT</li>
 * </ul>
 *
 * @author danillip
 * @version 1.0
 */

public class CollectionWorkWithFile {

    /**
     * Максимальное количество записей, которые будут выведены в консоль
     */
    private static final int MAX_PRINT = 10;

    /**
     * Метод main – точка входа в программу. Выполняет чтение CSV, вывод первых
     * {@link #MAX_PRINT} записей, а также рассчитывает статистику (min, max, avg)
     * по зарплатам сотрудников.
     *
     * @param args аргументы командной строки (не используются)
     */
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