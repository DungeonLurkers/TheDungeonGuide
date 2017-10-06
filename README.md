[![CircleCI](https://circleci.com/gh/Avabin/TheDungeonGuide/tree/master.svg?style=svg)](https://circleci.com/gh/Avabin/TheDungeonGuide/tree/master)
[![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg)](https://ktlint.github.io/)
# TheDungeonGuide
#### D&D management server written in JVM languages using Spring and Hibernate

## 1. Technologies and project characteristic:
##### 1.1. Technologies and libraries:

        a) Java 8 - main runtime
        b) Spring Boot 1.5.7.RELEASE
            - Spring Boot AOP
            - Spring Boot Data Rest
            - Spring Boot Security
            - Spring Session
            - Spring Boot Validation
            - Spring Boot Test
            - Spring RestDocs MockMVC
        c) Spring Data JPA 1.11.7.RELEASE
        d) Spring ORM 4.3.11.RELEASE
        e) Spring Security OAuth2 2.2.0.RELEASE
        f) Hibernate ORM 5.2.11.Final
        g) JDBC PostgreSQL Driver 42.1.4
        h) Lombok 1.16.18
        i) ModelMapper 1.1.0
        j) Kotlin 1.1.50
        k) Kotlin stdlib JRE8 1.1.50
        l) RxJava 2.1.3
        m) RxKotlin 2.1.0
        n) Python 3.6.2
        o) Pyhton Packages:
            - jsonpath_rw 1.4.0
            - pytest 3.2.2
            - PyYAML 3.12
            - Requests 2.18.4
            - PyRx 1.5.9
        p) RestPyTest
        r) Kotlinter 1.3.1
        s) Docker
        t) CircleCI
        u) TravicCI
        v) Heroku

##### 1.2. Characteristic:

TheDungeonGuide is project focused on maintaining RPG sessions 
of game called Dungeons And Dragons.
Currently it is limited to simple CRUD operation on database.
It uses my other project, RestPyTest to test simple restpoints with
a requests and response templates defined in YAML files. (.yml)

This particular project uses CircleCI as continous integration
service and Travis CI as deploy service.

After push or pull request new build is created. If tests passes
and build did not failed, new artifact is created and then 
deployed to Heroku by Travis.

## 2. How to run and use

   2.1. Set Environmental Variables

Env var name | example env var value | what it does
 --- | --- | ---
`POSTGRES_DB` | tdg_db | database name
`POSTGRES_USER` | postgres | used Postgres username
`POSTGRES_PASSWORD` | test | database password
`POSTGRES_URL` | jdbc:postgresql://localhost:5432/ | database URL

note: `POSTGRES_URL` is not used by Postgres. Rest of them are used in postgres and is application.

   2.2. Install and run Postgres (preferably by docker container)
   
Preferred (and tested) way to create new database to use with this project is by running local PostgreSQL container.
It should work with native postgresql installation if environmental variables are set correctly and database is created

note:  tables are not needed, Hibernate will create it automatically

If you have docker installed, you need to run new container with `5432` port exposed.
Example command:
`docker run -d -p 5432:5432 postgres:latest`

`-d` - detach from container

`-p` - publish port `CONTAINER_PORT:HOST_PORT`

   2.3. Build application
   
Just run `gradle build`

note: gradle 4.2 was used in project, don't know if older versions will work fine

   2.4. Run Python tests
    Packages from requirements are needed to be installed for test script to work.
    If you have Python 3.6 installed run `pip install -r python-tests/requirements.txt`
    from main project directory.
    
Server URL is defined in `test_rest.py` file.
    
Now, all needed python packages should be installed. Run test using this command `python -m pytest test_rest.py` 
from `python-tests` directory.

If all tests passed, no additional output is shown. When one test fail, script outputs all executed tests and
details about failing test to STDOUT.

New tests can be created by creating new `.yml` file in `templates` directory.

Each response is checked using jsonpath syntax and HTTP status codes. For example `$.name` refers to `name`
attribute in JSON response with a mapped value.

Example Add Item test:

```yaml
- message: Add Item
  method: POST
  endpoint: /item/add
  payload:
    name: TestItem
    price: 999
    desc: Test item created for testing purposes
  response:
    $.name: TestItem
    $.price: 999

- message: Get Item
  method: GET
  endpoint: /item/get/TestItem
  response:
    $.name: TestItem
    $.price: 999

- message: Delete Item
  method: DELETE
  endpoint: /item/del/TestItem
``` 

Three requests will be executed.

1. Add Item `POST` request on `URL/item/add` with a JSON request:

```json
{
  "name": "TestItem",
  "price": 999,
  "desc": "Test item created for testing purposes"
}
```

Response would look like this

```json
{
  "id": 1,
  "name": "TestItem",
  "price": 999,
  "desc": "Test item created for testing purposes"
}
```
where `$.name` is `TestItem` and `$.price` is `999`.

2. Get Item `GET` request on `URL/item/get/TestItem`

No request body. Response is Item object from database
with a matching name if found, or 404 NOT FOUND code otherwise. 

