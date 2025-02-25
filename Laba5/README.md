# AutoInjectable - Dependency Injection на Java

Проект демонстрирует реализацию автоматического внедрения зависимостей (**Dependency Injection**) в Java с использованием аннотаций и рефлексии.

## 🛠️ Основные компоненты

- **`@AutoInjectable`** - аннотация для автоматического внедрения зависимостей
- **`Injector`** - класс, выполняющий инъекцию зависимостей в поля, помеченные `@AutoInjectable`
- **`SomeBean`** - тестовый класс с аннотированными полями
- **Интерфейсы** `SomeInterface` и `SomeOtherInterface`, а также их реализации (`SomeImpl`, `OtherImpl`, `SODoer`)
- **Файл `dependency.properties`** - содержит информацию о том, какие классы должны инъектироваться в зависимости от интерфейсов

## 🚀 Как запустить

🔹 **Требования**: Java 17+ (или новее), Maven (для сборки).

1. **Склонируйте репозиторий**

2. **Откройте проект в IntelliJ IDEA**  
   (или другом IDE, поддерживающем Maven)

3. **Убедитесь, что `dependency.properties` находится в `src/main/resources`**
   ```properties
   org.example.interfaces.SomeInterface=org.example.impls.SomeImpl
   org.example.interfaces.SomeOtherInterface=org.example.impls.SODoer
   ```

4. **Запустите `Reflecs.java`** (`org.example.Reflecs`)  
   Ожидаемый вывод в консоли:
   ```
   A
   C
   ```

## 🔧 Изменение поведения

Если изменить `dependency.properties`, например:
```properties
org.example.interfaces.SomeInterface=org.example.impls.OtherImpl
```  
то при запуске вместо `A` появится `B`:
```
B
C
```

## 🛠 Сборка и тестирование

### 📌 Сборка с помощью Maven
```sh
mvn clean package
```

### 📌 Запуск тестов
```sh
mvn test
```

## 🏗️  Структура проекта

```
AutoInjectable/
│── src/
│   ├── main/
│   │   ├── java/org/example/
│   │   │   ├── annotations/AutoInjectable.java
│   │   │   ├── injector/Injector.java
│   │   │   ├── beans/SomeBean.java
│   │   │   ├── interfaces/SomeInterface.java
│   │   │   ├── interfaces/SomeOtherInterface.java
│   │   │   ├── impls/SomeImpl.java
│   │   │   ├── impls/OtherImpl.java
│   │   │   ├── impls/SODoer.java
│   │   │   ├── Reflecs.java
│   │   ├── resources/dependency.properties
│   ├── test/
│   │   ├── java/org/example/InjectorTest.java
```

---
✍️ Разработано для лабораторной работы по Java

👨‍💻 **Автор**: danillip
📅 **Версия**: 1.0  

