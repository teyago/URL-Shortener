# Url Shortener service

---
**How to install and run:**

* git clone https://github.com/teyago/URL-Shortener
* go to src\main\resources
* configure application.properties

**cmd:**

* mvn spring-boot:run

---
**How to use:**

**Postman**:

_POST_

    http://localhost:8080/tinyurl/generate

JSON body:

    {

        "url": "{url}",
    
        "expirationDate": "2022-10-10 10:10"

    }

will return a short link (alias) in the response

"expirationDate" is optional, by default expiration date is current time + 1 week

format is "yyyy-MM-dd HH:mm"

_GET_

    http://localhost:8080/tinyurl/{alias}

will redirect you to the original url

_GET_

    http://localhost:8080/tinyurl/{alias}/info/

will give you info about url such as: number of click, original url, alias, creation and expiration dates

_DELETE_

    http://localhost:8080/tinyurl/delete/{alias}

---
Also, every 10 second the program checks for expired URL's due the scheduler