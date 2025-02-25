package org.example;

import java.time.LocalDate;

/**
 * Класс Person хранит данные о человеке:
 * <ul>
 *     <li>{@code id} – идентификатор</li>
 *     <li>{@code name} – имя</li>
 *     <li>{@code gender} – пол</li>
 *     <li>{@code birthDate} – дата рождения</li>
 *     <li>{@code division} – подразделение (ссылка на {@link Division})</li>
 *     <li>{@code salary} – зарплата</li>
 * </ul>
 *
 * Экземпляры этого класса формируются при чтении CSV-файла в {@link CSVReaderUtil}.
 *
 * @author
 *     danillip
 * @version
 *     1.0
 */

public class Person {

    /**
     * Уникальный идентификатор человека (берётся из CSV).
     */
    private long id;

    /**
     * Имя человека.
     */
    private String name;

    /**
     * Пол человека (например, Male/Female).
     */
    private String gender;

    /**
     * Дата рождения в формате {@link java.time.LocalDate}.
     */
    private LocalDate birthDate;

    /**
     * Подразделение, в котором числится человек.
     */
    private Division division;

    /**
     * Зарплата сотрудника.
     */
    private double salary;

    /**
     * Конструктор по умолчанию (нужен для случаев, когда не хотим сразу задавать поля).
     */
    public Person() {
        // Конструктор по умолчанию
    }


    /**
     * Полноценный конструктор для инициализации всех полей человека.
     *
     * @param id          идентификатор
     * @param name        имя
     * @param gender      пол
     * @param birthDate   дата рождения
     * @param division    подразделение
     * @param salary      зарплата
     */
    public Person(long id, String name, String gender, LocalDate birthDate, Division division, double salary) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.division = division;
        this.salary = salary;
    }

    /**
     * Возвращает идентификатор человека.
     * @return {@link #id}
     */
    public long getId() {
        return id;
    }

    /**
     * Устанавливает идентификатор человека.
     * @param id новое значение идентификатора
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Возвращает имя человека.
     * @return {@link #name}
     */
    public String getName() {
        return name;
    }

    /**
     * Устанавливает имя человека.
     * @param name новое имя
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Возвращает пол человека.
     * @return {@link #gender}
     */
    public String getGender() {
        return gender;
    }


    /**
     * Устанавливает пол человека.
     * @param gender новый пол
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Возвращает дату рождения.
     * @return {@link #birthDate}
     */
    public LocalDate getBirthDate() {
        return birthDate;
    }

    /**
     * Устанавливает дату рождения.
     * @param birthDate новая дата рождения
     */
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Возвращает подразделение сотрудника.
     * @return {@link #division}
     */
    public Division getDivision() {
        return division;
    }

    /**
     * Устанавливает подразделение сотрудника.
     * @param division новое подразделение
     */
    public void setDivision(Division division) {
        this.division = division;
    }

    /**
     * Возвращает зарплату сотрудника.
     * @return {@link #salary}
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Устанавливает зарплату сотрудника.
     * @param salary новая зарплата
     */
    public void setSalary(double salary) {
        this.salary = salary;
    }

    /**
     * Возвращает строковое представление объекта {@code Person}, включающее:
     * <ul>
     *     <li>id</li>
     *     <li>name</li>
     *     <li>gender</li>
     *     <li>birthDate</li>
     *     <li>division</li>
     *     <li>salary</li>
     * </ul>
     *
     * @return строка вида:
     *         {@code Person{id=..., name='...', gender='...', birthDate=..., division=..., salary=...}}
     */
    @Override
    public String toString() {
        return String.format("Person{id=%d, name='%s', gender='%s', birthDate=%s, division=%s, salary=%.2f}",
                id, name, gender, birthDate, division != null ? division.getName() : "null", salary);
    }
}