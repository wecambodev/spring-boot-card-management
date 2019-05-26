# Card Management

This sample   task is to create a RESTful micro-service for managing debit / credit cards.

This exercise is just focused on the REST API and build with spring boot and mybatis with MySQL Database.

 
## Features

* SignUp /Signin /Generate Token 
* Fetch a list of cards for a specific user
* Fetch the card details for a specific card
* Activate a card
* Deactivate a card 
* Change the daily limit on the card


## Application Workflow Diagram

*  1.Consumer Signup and signin to generate ```token```
*  2.Use ```token``` to create Accounts 
*  3.Use ```token``` create Cards 
*  4.use ```token``` to make transactions



## Database

### Tables 
 There are three tables for storage data.
 
 * User  
 
 ```
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(191) DEFAULT 'user',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status` varchar(1) DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 ```
  
 
 * Card  
 
 ```
CREATE TABLE `cards` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `card_number` varchar(255) DEFAULT NULL,
  `card_type` varchar(10) DEFAULT '1' COMMENT '1: VISA, 2: MASTER',
  `expired_date` date DEFAULT NULL,
  `csv` varchar(3) DEFAULT NULL,
  `daily_limit` decimal(10,0) DEFAULT NULL,
  `status` tinyint(1) DEFAULT '1',
  `holder_name` varchar(191) DEFAULT NULL,
  `address_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

 ```
 
 * Address
 
 ```
CREATE TABLE `address` (
  `id` int(11) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `district` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `postal_code` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
COMMIT;

 ```
 
 
### Data For test 




## Frameworks 

To complete this  project tasks I use framework like below:   

* Spring Boot [2.1.5](https://spring.io/projects/spring-boot)
* Mybatis + Spring Boot    [2.0.1](http://www.mybatis.org/spring-boot-starter/) 
* Springfox Swagger2  [2.4.0](https://springfox.github.io/springfox/docs/current/)
* JWT   [2.4.0](https://github.com/jwtk/jjwt)
* Lombok   [1.18.8](https://projectlombok.org/features/all)
* Git client



## API Documentation 

* Swagger HTML Interface For easy understand how to use API
    ```
     http://localhost:8080/swagger-ui.html
    ```


### Authenication 

* Sign Up

    ```
    post: localhost:8080/v1/api/auth/signup
    ```

* Sign In

    ```
    post:localhost:8080/v1/api/auth/signin
    ```




### Consumer 

* Detail information consumer use (````token````)

  ```
    get: localhost:8080/v1/api/consumer/me
    
  ```


### Card 


* Fetch a list of cards for a specific user

    ```
    get: localhost:8080/v1/api/card/list
    
* Fetch the card details for a specific card

    ```
    get: localhost:8080/v1/api/card/list
    
    ```

* Activate the card

    ```
    put: localhost:8080/v1/api/card/activate
    
    ```
    
* Deactivate the card

    ```
    put: localhost:8080/v1/api/card/deactivate
    
    ```
    
* Change Limit on the card

    ```
    put: localhost:8080/v1/api/card/daily-limit
    
    ```








## references

