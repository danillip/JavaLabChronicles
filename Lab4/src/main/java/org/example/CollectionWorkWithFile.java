package org.example;

import java.util.List;

/**
 * Главный класс с методом main, который читает CSV и выводит результат
 */
public class CollectionWorkWithFile {

    // TODO: добавить обработку исключений, проверку размера списка и прочее
    public static void main(String[] args) {
        try {
            List<Person> people = CSVReaderUtil.readPeopleFromCSV("foreign_names.csv");
            System.out.println("Считано людей: " + people.size());
            for (Person p : people) {
                System.out.println(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
