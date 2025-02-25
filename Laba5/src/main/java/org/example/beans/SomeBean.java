package org.example.beans;

import org.example.annotations.AutoInjectable;
import org.example.interfaces.SomeInterface;
import org.example.interfaces.SomeOtherInterface;

/**
 * SomeBean с аннотированными полями
 * TODO: Проверить что поля будут инъектироваться
 */
public class SomeBean {
    @AutoInjectable
    private SomeInterface field1;

    @AutoInjectable
    private SomeOtherInterface field2;

    public void foo() {
        // Вызываем методы, чтобы убедиться в инъекции
        field1.doSomething();     // должно вывести A или B
        field2.doSomeOther();     // должно вывести C
    }
}
