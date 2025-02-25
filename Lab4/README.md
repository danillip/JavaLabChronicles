# 🚀 Java CSV Parser

## 📌 Описание проекта

Этот проект представляет собой Java-приложение для работы с CSV-файлом **foreign_names.csv**, содержащим информацию о людях.
Программа считывает данные из CSV-файла, обрабатывает их, формирует объекты Java и выполняет анализ данных.

## 🛠️ Функциональность

- Чтение данных из CSV-файла с помощью **OpenCSV**.
- Создание объектов `Person` и `Division`.
- Ограниченный вывод данных в консоль (по умолчанию 10 записей).
- Подсчёт статистики по зарплатам (минимальная, максимальная, средняя).
- Использование коллекций Java (`List`, `Map`) для работы с данными.

## 🏗️ Структура проекта

```plaintext
JavaLabProject/
│   README.md
│   pom.xml
│
└───src
    ├───main
    │   ├───java/org/example
    │   │   ├── CollectionWorkWithFile.java  # Главный класс (точка входа)
    │   │   ├── CSVReaderUtil.java          # Класс для работы с CSV
    │   │   ├── Person.java                 # Класс для представления человека
    │   │   ├── Division.java               # Класс для подразделения
    │   └───resources
    │       └── foreign_names.csv           # CSV-файл с данными
    └───test
        ├───java/org/example
        │   ├── CSVReaderUtilTest.java      # Unit-тесты для CSV парсера
```

## 💻 Требования

- **Java 17+**
- **Maven 3+**
- **IntelliJ IDEA (или другая IDE с поддержкой Maven)**

## 🚀 Установка и запуск

### 🔧 Сборка проекта

```bash
mvn clean install
```

### 🚀 Запуск программы

```bash
mvn exec:java -Dexec.mainClass="org.example.CollectionWorkWithFile"
```

### 🧪 Запуск тестов

```bash
mvn test
```

## Используемые технологии

- **Java 17**
- **Maven**
- **OpenCSV**
- **JUnit 5**
- **Stream API**

👨‍💻 **Автор**: danillip
📅 **Версия**: 1.0