# Card Management

This is sample task to create a RESTful micro-service for managing debit / credit cards.

This exercise is just focused on the REST API and build with spring boot and mybatis with MySQL Database.

 
## Features

* SignUp/ Signin /Generate JWT Token 
* Fetch a list of cards for a specific user
* Fetch the card details for a specific card
* Activate a card
* Deactivate a card 
* Change the daily limit on the card



## Database


This project use MYSQL Database and sample database was include [download](https://raw.githubusercontent.com/wecambodev/spring-boot-card-management/master/src/main/resources/database.sql) it or you can find it in resource/database.sql

* Import to your Database and change url in ```application.properties``` 

```
    spring.datasource.url=jdbc:mysql://127.0.0.1:3306/bank-cards
```
    

## Frameworks and Libraries 

To complete this  project tasks I use framework like below:   

 * Spring Boot [2.1.5](https://spring.io/projects/spring-boot)
 * Mybatis + Spring Boot    [2.0.1](http://www.mybatis.org/spring-boot-starter/) 
 * Springfox Swagger2  [2.4.0](https://springfox.github.io/springfox/docs/current/)
 * JWT   [2.4.0](https://github.com/jwtk/jjwt)
 * Lombok   [1.18.8](https://projectlombok.org/features/all)



## API Documentation 

 * Swagger Generate HTML Interface For easy understand how to use API
    ```
     http://localhost:8080/swagger-ui.html
    ```


### Authenication 

 * Sign Up

    ```
    post: /v1/api/auth/signup
    ```

 * Sign In

    ```
    post:/v1/api/auth/signin
    ```




### Consumer 

 * Detail information consumer use (````token````)

  ```
    get: /v1/api/consumer/me
    
  ```


### Card 


 * Fetch a list of cards for a specific user

    ```
    get: /v1/api/card/list
    
 * Fetch the card details for a specific card

    ```
    get: /v1/api/card/detail
    
    ```

 * Activate the card
        
    ```
    put: /v1/api/card/activate
    
    ```
    
 * Deactivate the card

    ```
    put: /v1/api/card/deactivate
    
    ```
    
 * Change Limit on the card

    ```
    put: /v1/api/card/daily-limit
     
    ```
    
   


## Test Case Integration 

 This project includes test case 




## Conclusion  

This project setup from scratch I will improved it when i have more free times. I hope it can help you how to use spring boot + Mybatis to build restful API with Authorization ```Bearer``` JWT.


Thanks   

