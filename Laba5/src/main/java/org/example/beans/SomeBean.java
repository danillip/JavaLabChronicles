package org.example.beans;

import org.example.annotations.AutoInjectable;
import org.example.interfaces.SomeInterface;
import org.example.interfaces.SomeOtherInterface;

/**
 * Класс SomeBean, в котором используются поля для инъекции
 * Поля помечены аннотацией @AutoInjectable
 * TODO: Проверить что поля будут инъектироваться корректно
 */
public class SomeBean {
    @AutoInjectable
    private SomeInterface field1;

    @AutoInjectable
    private SomeOtherInterface field2;

    /**
     * Вызывает методы doSomething() и doSomeOther() у инъецированных полей
     * Ожидается, что field1 выведет A или B, а field2 выведет C
     */
    public void foo() {
        // Вызываем методы, чтобы убедиться в инъекции
        field1.doSomething();     // Должно вывести A (или B, если поменять реализацию)
        field2.doSomeOther();     // должно вывести C
    }
}
