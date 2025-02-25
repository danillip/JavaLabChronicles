package org.example;

import org.example.beans.SomeBean;
import org.example.injector.Injector;

/**
 * Точка входа для демонстрации работы инъекции
 * TODO: Запустить и убедиться, что выводит A и C по умолчанию
 * Если в properties изменить реализацию SomeInterface, будет B и C
 */
public class Reflecs {
    public static void main(String[] args) {
        SomeBean sb = new SomeBean();
        new Injector().inject(sb);
        sb.foo(); // ожидаем A и C
    }
}
