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



## Database

This project use MYSQL Database  [download](https://raw.githubusercontent.com/wecambodev/spring-boot-card-management/master/src/main/resources/database.sql) or you can find it in resource/database.sql

### Tables 
 There are three tables for storage data.
 
 * User  
 
     ```sql
    
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
 
     ```sql
    CREATE TABLE `cards` (
      `id` bigint(20) NOT NULL,
      `user_id` bigint(20) DEFAULT NULL,
      `card_number` varchar(255) DEFAULT NULL,
      `card_type` varchar(191) DEFAULT NULL,
      `expired_date` date DEFAULT NULL,
      `csv` varchar(3) DEFAULT NULL,
      `daily_limit` decimal(10,0) DEFAULT NULL,
      `status` tinyint(1) DEFAULT '1',
      `holder_name` varchar(191) DEFAULT NULL,
      `address_id` bigint(20) DEFAULT NULL
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
    
    
     ```
 
 * Address
 
     ```sql
    CREATE TABLE `address` (
      `id` int(11) UNSIGNED NOT NULL,
      `address` varchar(255) DEFAULT NULL,
      `district` varchar(255) DEFAULT NULL,
      `city` varchar(255) DEFAULT NULL,
      `postal_code` varchar(255) DEFAULT NULL,
      `country` varchar(255) DEFAULT NULL
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
    
     ```
 
 
### Data For test 

* User sample 

    ```sql
        INSERT INTO `users` (`id`, `phone_number`, `password`, `role`, `created_at`, `status`) VALUES
        (1, '093883292', '$2a$10$or9jKJtcnRpIjcTj62aczesC99ZFpw4G61zOOTii4HecjbKADb67u', 'consumer', '2019-05-26 12:25:46', '1'),
        (2, '086503225', '$2a$10$ynJXDlrrcz9qLRR4dHtdFeJve9xJ2gDwQsVNcpLfe8BvZ.u/KXIwW', 'consumer', '2019-05-26 12:51:04', '1');
  
    ```

* Cards Sample 

    ```sql
    
    INSERT INTO `cards` (`id`, `user_id`, `card_number`, `card_type`, `expired_date`, `csv`, `daily_limit`, `status`, `holder_name`, `address_id`) VALUES
    (1, 1, '4111 1111 1111 1111', 'visa', '2020-01-01', '222', '1000', 1, 'Phuong Phally', 1),
    (2, 1, '5500 0000 0000 0004', 'masterCard', '2020-01-01', '111', '1000', 1, 'Phuong Phally', 1),
    (3, 2, '4222 2222 2222 2222 ', 'visa', '2020-02-02', '111', '500', 0, 'Dara Penhchet', 2),
    (4, 2, '3400 0000 0000 009', 'masterCard', '2020-02-02', '999', '500', 1, 'Dara Penhchet', 2);

    ```

* Address Sample 

    
     ```sql
     
     INSERT INTO `address` (`id`, `address`, `district`, `city`, `postal_code`, `country`) VALUES
     (1, '61 Preah Monivong Blvd (93)', 'Toul Kork', 'Phnom Penh', '12102', 'Cambodia'),
     (2, 'Mao Tse Toung Boulevard (245)', 'Resey Keo', 'Phnom Penh', '120102', 'Cambodia');
    
    
     ```
    
    


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
    get: /v1/api/card/list
    
* Fetch the card details for a specific card

    ```
    get: /v1/api/card/list
    
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
    
    








## references



## Conclusion  

This project setup from scratch I will improved it when i have more free times. I hope it can help you how to use spring boot + mybastic to build restful API with authenication JWT.


Thanks   

