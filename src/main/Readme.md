Условие
Требуется реализовать сервис для получения данных об офисах Альфа-Банка и их загруженности.
Сервис должен предоставлять REST API на http://IP:8082.
Файл с описанием API – api.json.
Так как мы не сомневаемся, что вы умейте гуглить, дали вам немного подсказок для задачи и ссылок на статьи, чтобы экономить бесценное время, которого и так немного.

Данные лежат в PostgreSQL, который расположен в docker
Для запуска PostgreSQL:
mkdir task3 ; cd task3
wget https://raw.githubusercontent.com/evgenyshiryaev/alfa-battle-resources/master/task3/docker-compose.yml
wget https://raw.githubusercontent.com/evgenyshiryaev/alfa-battle-resources/master/task3/Dockerfile
wget https://raw.githubusercontent.com/evgenyshiryaev/alfa-battle-resources/master/task3/init_db.sql
docker-compose up -d
Для остановки (в папке task3):
docker-compose down
Адрес: IP:5432
DB: alfa_battle
Auth: alfa_battle / qwe123

* IP - внешний IP виртуальной машины.
* Все ресурсы лежат тут: https://github.com/evgenyshiryaev/alfa-battle-resources/tree/master/task3
Задачи
1. Получение данных офиса по id
Запрос: GET http://IP:8082/branches/{id}

Ответ:
- 200 Branches
- 404 ErrorResponse (если офис не найден)

Пример: GET http://IP:8082/branches/612
200
{
  "id": 612,
  "title": "Мясницкий",
  "lon": 37.6329,
  "lat": 55.7621,
  "address": "Мясницкая ул., 13, стр. 1"
}

Пример: GET http://IP:8082/branches/1
404
{
  “status”: “branch not found”
}

ЗАПУСК:
-----------------------------------------------------------------------------------
1) перед запуском программы запустить докер, для включения postgres и наполнения БД
2) swagger для тестирования: http://localhost:8082/swagger-ui.html