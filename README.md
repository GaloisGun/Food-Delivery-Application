# Food-Delivery-Application
A food delivery application using Microservices architecture and Spring Cloud

**Feature List**
- Upload Restaurant Infomation
- Delete All Restaurant Information
- Find Restaurant by name
- Order food by choosing different menu item, quantity 
- Add a note about his/her diet restrictions
- Upload and valid payment infomation
- Show order confirmation
- Use Eureka to discovery services
- Use Hystrix as circuit breaker
- Use RabbitMQ as message queue between services

##Requirements
-   JAVA 8
-   Apache Maven
-   Docker
-   Docker Compose

##Installation and Start
### 1. Download
````
git clone git@github.com:GaloisGun/Food-Delivery-Application.git
````

### 2.  Run MySQL Sever
> Configure MySQL image version in `docker-compose.yml`.
```
docker-compose up -d
```

### 3.  Run Services
> Use .sh document to run the micro-services
````
sh ./start-eureka-service.sh
sh ./start-hystrix-service.sh
sh ./start-payment-distribution.sh
sh ./start-payment-validator.sh
sh ./start-restaurant-service.sh
sh ./start-order-service.sh
````


##API Overview
>   Check API References for detailed information

| Method | URL                             | Description                      | 
|--------|---------------------------------|----------------------------------|
|POST    | /restaurant                     | Upload a list of RestaurantInfo  |
|DELETE  | /restaurant/purge               | Delete all RestaurantInfo        |
|GET     | /restaurant/{name}              | Get one Restaurant by name       |
|POST    | /restaurant/menu                | Upload a list of Menu            |
|GET     | /restaurant/{restaurantId}/menu | Get menu by restaurantId         |
|DELETE  | /restaurant/menu                | Delete all Menu                  |
|POST    | /restaurant/{restaurantId}/order| Post Orders by RestaurantId      |
|POST    | /restaurant/order               | Post Orders                      |
|GET     | /restaurant/order               | Get Orders by RestaurantId       |
|GET     | /restaurant/order/{orderId}     | Get Order comfirmation by orderId|
|PUT     | /restaurant/order/{orderId}     | Update the Order Status          |
|DELETE  | /restaurant/order/{orderId}     | Delete the order by Id           |
