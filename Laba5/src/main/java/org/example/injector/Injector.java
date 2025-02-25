package org.example.injector;

import org.example.annotations.AutoInjectable;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * Injector
 * UPD: Добавлены JavaDoc и логика обработки ошибок
 */
public class Injector {

    private Properties properties;

    /**
     * Конструктор, загружающий настройки зависимостей из dependency.properties
     * Если файл не найден, будет выведено предупреждение
     */
    public Injector() {
        properties = new Properties();
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("dependency.properties")) {
            if (is != null) {
                properties.load(is);
            } else {
                System.err.println("Файл dependency.properties не найден в ресурсах.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Выполняет инъекцию зависимостей в поля объекта, которые помечены аннотацией @AutoInjectable
     * Для определения реализации по типу поля используется файл properties
     *
     * @param <T> тип инжектируемого объекта
     * @param obj экземпляр, в поля которого будут внедрены зависимости
     * @return возвращает тот же экземпляр, с уже установленными зависимостями
     */
    public <T> T inject(T obj) {
        if (obj == null) {
            return null;
        }
        Class<?> cl = obj.getClass();
        Field[] fields = cl.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(AutoInjectable.class)) {
                Class<?> fieldType = field.getType();
                String implName = properties.getProperty(fieldType.getName());
                if (implName != null) {
                    try {
                        Class<?> implClass = Class.forName(implName);
                        Object implInstance = implClass.getDeclaredConstructor().newInstance();
                        field.setAccessible(true);
                        field.set(obj, implInstance);
                    } catch (Exception e) {
                        // Если что-то пошло не так
                        System.err.println("Ошибка при инъекции поля " + field.getName() + ": " + e.getMessage());
                    }
                } else {
                    System.err.println("Не найдена реализация для " + fieldType.getName());
                }
            }
        }
        return obj;
    }
}