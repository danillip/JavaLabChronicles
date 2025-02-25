package org.example.injector;

import org.example.annotations.AutoInjectable;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * Базовая реализация метода inject которая читает properties
 * TODO: Дополнить реальной логикой и проверками
 */
public class Injector {

    private Properties properties;

    public Injector() {
        properties = new Properties();
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("dependency.properties")) {
            if (is != null) {
                properties.load(is);
            } else {
                // TODO: Обработать случай, когда файла нет
                System.err.println("dependency.properties не найден!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод для инъекции зависимостей в поля объекта, отмеченные @AutoInjectable.
     * @param <T> тип объекта
     * @param obj сам объект, поля которого хотим заполнить
     * @return тот же объект, но с заполненными полями
     */
    public <T> T inject(T obj) {
        Class<?> cl = obj.getClass();
        Field[] fields = cl.getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(AutoInjectable.class)) {
                // Получаем интерфейс (или класс) поля
                Class<?> fieldType = field.getType();
                // Ищем в properties
                String implName = properties.getProperty(fieldType.getName());
                if (implName != null) {
                    try {
                        // Грузим класс реализации
                        Class<?> implClass = Class.forName(implName);
                        Object implInstance = implClass.getDeclaredConstructor().newInstance();

                        // Делаем поле доступным для записи
                        field.setAccessible(true);
                        field.set(obj, implInstance);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    // TODO: обработать случай когда реализации нет в properties
                    System.err.println("Для типа " + fieldType.getName() + " не найдена реализация в properties");
                }
            }
        }

        return obj;
    }
}