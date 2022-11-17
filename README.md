![image](game-catalogue-backend/src/main/resources/game-catalogue.png?raw=true)

## Game catalogue
The goal of an application was to create a game catalogue.
In this <b>Application</b> user can:
- Register, login, logout 
- Add or delete games from cart
- Admin users can add new games and update old ones (backend only) 
- Unregistered users can check out and receive list of keys for games (backend only)
- Registered user can check out and games will be added to the library (backend only)

## Technologies used
###Backend
```
Java 11, Spring Boot 2.7.5, MySQL
```
###Frontend
```
TypeScript, React.js, ChakraUI
```
For user auth I implemented jwt-token based authentication, HttpSession id is used for storing cart.

You can find postman.json file to check endpoints or start an application and go to ${server:port}/swagger-iu/ page 

On the start of the application user with role admin is created. Use username:admin password:admin for login

Frontend made with React.js and ChakraUi.
## Installation
###Backend
Create schema for application from file `table-create.sql`.

Specify variables for `application.properties`.

To start backend run:
```
mvn spring-boot:run
```
###Frontend
To start frontend run:
```
yarn install
yarn start
```
[http://localhost:3000](http://localhost:3000) to view application in the browser.

