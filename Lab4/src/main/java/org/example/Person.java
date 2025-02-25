package org.example;

import java.time.LocalDate;

public class Person {
    private long id;
    private String name;
    private String gender;
    private LocalDate birthDate;
    private Division division;
    private double salary;

    public Person() {
        // Конструктор по умолчанию
    }

    public Person(long id, String name, String gender, LocalDate birthDate, Division division, double salary) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.division = division;
        this.salary = salary;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return String.format("Person{id=%d, name='%s', gender='%s', birthDate=%s, division=%s, salary=%.2f}",
                id, name, gender, birthDate, division != null ? division.getName() : "null", salary);
    }
}