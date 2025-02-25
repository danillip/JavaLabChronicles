package org.example.injector;

import org.example.annotations.AutoInjectable;

/**
 * Черновой вариант класса Injector
 * TODO: Реализовать поиск аннотированных полей и внедрение зависимостей
 * UPD: Пока только каркас
 */
public class Injector {

    /**
     * Метод для инъекции зависимостей в поля объекта, отмеченные @AutoInjectable
     * @param <T> тип объекта
     * @param obj сам объект, поля которого хотим заполнить
     * @return тот же объект, но с заполненными полями (если найдены аннотации)
     */
    public <T> T inject(T obj) {
        // TODO: Реализовать
        System.out.println("Заглушка метода inject()");
        return obj;
    }
}
