package org.example.impls;

import org.example.interfaces.SomeOtherInterface;

/**
 * Реализация SomeOtherInterface
 * Выводит C при вызове метода doSomeOther
 */
public class SODoer implements SomeOtherInterface {
    @Override
    public void doSomeOther() {
        System.out.println("C");
    }
}
