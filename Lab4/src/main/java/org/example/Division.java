package org.example;

/**
 * Класс Division хранит данные о подразделении:
 * <ul>
 *     <li>id – уникальный идентификатор (генерируется автоматически)</li>
 *     <li>name – название подразделения</li>
 * </ul>
 * <p>
 * Экземпляры этого класса могут переиспользоваться разными {@link Person},
 * поэтому при чтении CSV используется общий {@code Map<String, Division>}
 * для предотвращения дубликатов
 * </p>
 *
 * @author
 *     danillip
 * @version
 *     1.0
 */

public class Division {

    /**
     * Статический счётчик для генерации уникальных идентификаторов.
     */
    private static long divisionIdCounter = 1;

    /**
     * Уникальный идентификатор подразделения.
     */
    private long id;

    /**
     * Название подразделения.
     */
    private String name;

    /**
     * Конструктор без параметров. Присваивает
     * {@link #name} значение "Unknown Division" и генерирует {@link #id}.
     */
    public Division() {
        // При создании без имени – просто примерчик
        this.id = divisionIdCounter++;
        this.name = "Unknown Division";
    }

    /**
     * Конструктор, создающий подразделение с заданным названием.
     * @param name название подразделения
     */
    public Division(String name) {
        this.id = divisionIdCounter++;
        this.name = name;
    }


    /**
     * Возвращает идентификатор подразделения.
     * @return {@link #id}
     */
    public long getId() {
        return id;
    }

    /**
     * Возвращает название подразделения.
     * @return {@link #name}
     */
    public String getName() {
        return name;
    }

    /**
     * Устанавливает название подразделения.
     * @param name новое название
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Возвращает строковое представление Division.
     * @return "Division{id=..., name='...'}"
     */
    @Override
    public String toString() {
        return "Division{id=" + id + ", name='" + name + "'}";
    }
}