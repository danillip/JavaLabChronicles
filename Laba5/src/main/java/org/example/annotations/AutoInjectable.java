package org.example.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Аннотация для автоматической инъекции зависимостей
 * Может помечать любые поля, требующие внедрения конкретных реализаций
 * Поле должно иметь тип интерфейса или абстрактного класса
 * TODO: Проверить, что аннотация доступна в runtime
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoInjectable {
    // Аннотация-маркер, не содержит никаких полей
}
