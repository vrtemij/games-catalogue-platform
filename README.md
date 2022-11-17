![image](game-catalogue-backend/src/main/resources/game-catalogue.png?raw=true)

## Game catalogue
The goal of an application was to create a game catalogue.
In this <b>Application</b> user can:
* Register, login, logout 
* Add or delete games from cart
* Admin users can add new games and update old ones (backend only) 
* Unregistered users can check out and receive list of keys for games (backend only)
* Registered user can check out and games will be added to the library (backend only)

## Technologies used

### Backend

```
Java 11, Spring Boot 2.7.5, MySQL
```

### Frontend

```
TypeScript, React.js, ChakraUI
```

## Installation

### Backend
* Create schema for application from file `table-create.sql`.
* Specify variables for `application.properties`.

To start backend run:

```
mvn spring-boot:run
```

### Frontend

To start frontend run:

```
yarn install
yarn start
```

Visit [http://localhost:3000](http://localhost:3000) to view application in the browser.

### Notes
* For user auth I implemented jwt-token based authentication, HttpSession id is used for storing user cart.
On the start of the application admin user is created, use `{"username": "admin", "password": "admin"}` to login.
* Visit [swagger-ui](http://localhost:8080/swagger-iu/) to view application api or export `platform.postman_collection.json` to postman.

