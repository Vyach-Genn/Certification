# Certification

Кратко: проект предназначен для фильтрации и проверки корректности данных о перелётах. Реализует набор фильтров для рейсов, а также тесты для проверки их работы.

## Структура
- `src/main/java/com/gridnine/testing/model` — модели данных (Flight, Segment)
- `src/main/java/com/gridnine/testing/filters` — фильтры для рейсов
- `src/main/java/com/gridnine/testing/util` — вспомогательные классы
- `src/main/java/com/gridnine/testing/Main.java` — точка входа

## Фильтры
В проекте реализованы следующие фильтры для рейсов:
- Исключение сегментов с вылетом до текущего момента
- Исключение сегментов, где время прилёта раньше времени вылета
- Исключение рейсов с суммарным временем на земле между сегментами более 2 часов

## Тесты
- Реализованы тестовые классы для фильтров:
  - DepartureBeforeNowFilter
  - ExcessiveGroundTimeFilter
  - ArrivalBeforeDepartureFilter
- Тесты находятся в `src/test/java/com/gridnine/testing/filters`

## Сборка и запуск
- Сборка: `mvn package`
- Запуск: `Main.java` или через Maven 