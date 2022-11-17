## Summary

The goal of an application was to create a game catalogue.
User can check the catalogue of available games, add game to cart, place an order, register and login.

## Technologies used
Backend made with Java 11, Spring boot and MySQL. 
For user auth I implemented jwt-token based authentication, HttpSession id is used for storing cart.

You can find postman.json file to check endpoints or start an application and go to ${server:port}/swagger-iu/ page 

On the start of the application user with role admin is created. Use username:admin password:admin for login

Frontend made with React.js and ChakraUi.
## How to start application
###Backend
Check application.properties file and specify all required variables. 
You can find table-create.sql for table creation.

For starting backend run the following commands:
-mvn spring-boot:run
###Frontend
For starting frontend run the following commands:
-yarn install
-yarn start


