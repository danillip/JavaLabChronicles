package org.example.annotations;

/**
 * Аннотация для автоматической инъекции зависимостей
 * TODO: Проверить, что аннотация доступна в runtime
 */
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface AutoInjectable {
    // Аннотация-маркер, не содержит никаких полей
}
