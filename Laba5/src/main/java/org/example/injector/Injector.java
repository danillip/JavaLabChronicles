package org.example.injector;

import org.example.annotations.AutoInjectable;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * Класс Injector, который отвечает за чтение properties-файла
 * и инъекцию зависимостей в поля, помеченные аннотацией @AutoInjectable
 * UPD: Обрабатывает ошибки при загрузке классов
 */
public class Injector {

    /**
     * Объект, содержащий ключ-значение в формате:
     * имя интерфейса = имя реализации
     */
    private Properties properties;

    /**
     * Конструктор, загружающий настройки зависимостей из dependency.properties
     * Если файл не найден, выводится предупреждение
     * TODO: Обработать более детально ошибки чтения, если необходимо
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
     * Выполняет инъекцию зависимостей в поля объекта
     * Поля должны быть помечены аннотацией @AutoInjectable
     * Назначаем реализации на основе конфигурации, указанной в properties
     *
     * @param <T> тип инжектируемого объекта
     * @param obj объект, в поля которого будут внедрены зависимости
     * @return тот же объект obj, но с заполненными полями
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