package org.example.impls;

import org.example.interfaces.SomeInterface;

/**
 * OtherImpl
 * UPD: Другая реализация SomeInterface
 */
public class OtherImpl implements SomeInterface {
    @Override
    public void doSomething() {
        System.out.println("B");
    }
}
