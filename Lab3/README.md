# Collection Performance Benchmark

Этот проект сравнивает производительность `ArrayList` и `LinkedList` в Java, измеряя время выполнения основных операций:

- **Добавление (`add`)**
- **Удаление (`remove`)**
- **Получение (`get`)**

## 📜 Описание

Проект реализован на **Java 17+** и использует **Maven** для управления зависимостями и тестирования.  
Тестирование выполняется с использованием **JUnit 5**, а результаты выводятся в удобном табличном формате.

## 📂 Структура проекта

```
CollectionPerformance/
 ├── src/
 │  ├── main/
 │  │  └── java/com/example/CollectionWork.java
 │  └── test/
 │     └── java/com/example/CollectionWorkTest.java
 ├──pom.xml
 └──README.md
```

## 💻 Установка и запуск

### 🔧 Сборка
```bash
mvn clean package
```

### 🚀 Запуск
```sh
mvn exec:java -Dexec.mainClass="com.example.CollectionWork"
```

## 📊 Пример вывода

```
=== Результаты (Финал) ===
Operation  | Iterations         | ArrayList (ns)     | LinkedList (ns)
-------------------------------------------------------------------
Add        | 1000               | 1345000            | 3457000
Remove     | 1000               | 2875000            | 8234000
Get        | 1000               | 870000             | 20987000
```

## 🧪 Тестирование

Запустите юнит-тесты:
```sh
mvn test
```

---

👨‍💻 **Автор**: danillip
📅 **Версия**: 1.0