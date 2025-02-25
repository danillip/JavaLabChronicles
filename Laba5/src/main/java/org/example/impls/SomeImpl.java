package org.example.impls;

import org.example.interfaces.SomeInterface;

/**
 * Реализация SomeInterface
 * Выводит A при вызове метода doSomething
 * UPD: Может быть заменён на другую реализацию, если нужно
 */
public class SomeImpl implements SomeInterface {

    @Override
    public void doSomething() {
        System.out.println("A");
    }
}
