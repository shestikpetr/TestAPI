# TestAPI

Микросервисное приложение для создания и прохождения тестов/квизов.

## Стек технологий

- **Kotlin** + **Spring Boot 4.0.2**
- **Java 21**
- **PostgreSQL** — база данных для каждого сервиса
- **Flyway** — миграции БД
- **Spring Security** + **JWT** — аутентификация и авторизация
- **SpringDoc OpenAPI (Swagger)** — документация API
- **Docker Compose** — оркестрация сервисов
- **Gradle** (Kotlin DSL) — сборка

## Архитектура

Проект состоит из 5 микросервисов:

```
TestAPI/
├── api-gateway/       — API-шлюз (порт 8080)
├── auth-service/      — Аутентификация, JWT (порт 8081)
├── user-service/      — Пользователи и группы (порт 8082)
├── test-service/      — Тесты, вопросы, ответы (порт 8082)
├── media-service/     — Медиафайлы (порт 8083)
└── compose.yaml       — Docker Compose конфигурация
```

### auth-service

Сервис аутентификации и авторизации.

- Регистрация и логин (`/api/v1/auth/register`, `/api/v1/auth/login`)
- Генерация и валидация JWT-токенов
- Хранение учетных данных (email, password hash, role)
- Swagger UI: `http://localhost:8081/api/v1/docs`

### user-service

Управление пользователями и группами.

- CRUD для пользователей (`/api/v1/users`)
- CRUD для групп (`/api/v1/groups`)
- Членство в группах с ролями: `OWNER`, `ADMIN`, `MEMBER`
- Системные роли: `USER`, `MODERATOR`, `ADMIN`
- Swagger UI: `http://localhost:8082/api/v1/docs`

### test-service

Управление тестами, вопросами и ответами.

- Сущности: `Test` → `Question` → `Answer` (one-to-many)
- CRUD для тестов (`/api/v1/tests`)

### api-gateway

API-шлюз для маршрутизации запросов между сервисами. В стадии разработки.

### media-service

Сервис для работы с медиафайлами. В стадии разработки.

## Структура БД

### auth-service (`auth_db`)

| Таблица       | Описание                              |
|---------------|---------------------------------------|
| `credentials` | email, password_hash, user_id, role   |

### user-service (`user_db`)

| Таблица             | Описание                                    |
|---------------------|---------------------------------------------|
| `users`             | Профили пользователей                       |
| `groups`            | Группы                                      |
| `group_memberships` | Связь пользователей и групп с ролями        |

### test-service (`test_db`)

| Таблица     | Описание                |
|-------------|-------------------------|
| `tests`     | Тесты                   |
| `questions` | Вопросы (связь с тестом) |
| `answers`   | Ответы (связь с вопросом)|
