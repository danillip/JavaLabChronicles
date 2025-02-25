package org.example;

import org.example.beans.SomeBean;
import org.example.injector.Injector;

/**
 * Reflecs main(), чтобы запустить и проверить
 * TODO: Запустить, убедиться, что выводит A + C
 */
public class Reflecs {
    public static void main(String[] args) {
        SomeBean sb = new SomeBean();
        new Injector().inject(sb);
        sb.foo(); // ожидаем A и C
    }
}
