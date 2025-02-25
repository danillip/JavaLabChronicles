package org.example.impls;

import org.example.interfaces.SomeInterface;

/**
 * Другая реализация SomeInterface
 * Выводит B при вызове метода doSomething
 */
public class OtherImpl implements SomeInterface {
    @Override
    public void doSomething() {
        System.out.println("B");
    }
}
