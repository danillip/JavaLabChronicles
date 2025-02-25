package org.example;

/**
 * Класс Division хранит данные о подразделении:
 * <ul>
 *     <li>id – уникальный идентификатор, генерируется автоматически</li>
 *     <li>name – название подразделения</li>
 * </ul>
 */

public class Division {
    private static long divisionIdCounter = 1;

    private long id;
    private String name;

    public Division() {
        // При создании без имени – просто примерчик
        this.id = divisionIdCounter++;
        this.name = "Unknown Division";
    }

    public Division(String name) {
        this.id = divisionIdCounter++;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Division{id=" + id + ", name='" + name + "'}";
    }
}