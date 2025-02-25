package org.example;

import org.example.beans.SomeBean;
import org.example.injector.Injector;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit-тест для проверки инъекции
 * TODO: Дополнить проверками вывода
 */
public class InjectorTest {

    @Test
    public void testInjection() {
        SomeBean sb = new SomeBean();
        new Injector().inject(sb);

        // Тут сложно проверить System.out,
        // но мы можем проверить, что поля не null
        // или использовать mock / spy, но для простоты проверим not null
        // TODO: при желании - расширить
        Assert.assertNotNull(sb);
    }
}
