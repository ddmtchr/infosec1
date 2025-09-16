# Романенко М. Р. Лабораторная 1

## Пользователи

Существуют 2 in-memory-пользователя: admin/123 и user/456.

## Эндпоинты

- `POST /auth/login?username=username&password=password` - аутентификация пользователя. Возвращает JSON вида
```json
{
    "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTc1ODA1NjY3OSwiZXhwIjoxNzU4MDYwMjc5fQ.xh7-dxVmcshTkRrVFqT-au6nj3WeotOnl3ICpgiQyNGBd2G52ZzN6o4Rc5TxDaWbNt1RLZy42o9r9WFqK4o5XQ",
    "refreshToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTc1ODA1NjY3OSwiZXhwIjoxNzU4NjYxMjAwfQ.rHO58OXt28zVVmGmflvPjY7lLwsIRDYESw8Ngu2cYszsNpkLWkOP1I2QuNXrTO-bcavXDGwKENLdJ2OdeshU9g"
}
```
- `POST /auth/refresh` - получение нового accessToken по refreshToken. Возвращает accessToken и refreshToken в JSON. Возвращает вышеуказанный JSON. Принимает JSON вида
```json
{
    "refreshToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTc1ODA1NjY3OSwiZXhwIjoxNzU4NjYxMjAwfQ.rHO58OXt28zVVmGmflvPjY7lLwsIRDYESw8Ngu2cYszsNpkLWkOP1I2QuNXrTO-bcavXDGwKENLdJ2OdeshU9g"
}
```
- `GET /api/posts` - получить список всех постов. Доступен только аутентифицированному пользователю с заголовком Authorization: Bearer ... . Возвращает JSON вида
```json
[
  {
    "title": "5 пост",
    "content": "4 пост",
    "author": "user"
  }
]

```
- `POST /api/posts` - создать новый пост. Доступен только аутентифицированному пользователю с заголовком Authorization: Bearer ... . Принимает тело запроса вида
```json
{
    "title": "5 пост",
    "content": "4 пост",
    "author": "user"
}
```

## Реализованные меры защиты

- Аутентификация по JWT:
  - По запросу на аутентификацию сверяется логин и пароль in-memory пользователей
  - JwtProvider создает accessToken и refreshToken и выдает их пользователю
  - Фильтр JwtAuthorizationFilter расшифровывает и валидирует токен при каждом запросе на защищенный эндпоинт
  - По запросу на обновление токена выдается новый accessToken
- Защита от SQL-инъекций - используется Spring Data и ORM Hibernate
- Защита от XSS - экранирование специальных символов HTML в теле ответа с помощью HtmlUtils.htmlEscape() (в PostService)