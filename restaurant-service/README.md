#Restaurant-Service
**Restaurant-Service** is a RESTful service in Spring Boot, using Maven as build tool.

**Feature List**
- Upload Restaurant Infomation
- Delete All Restaurant Information
- Find Restaurant by name

##Requirements
-   JAVA 8
-   Apache Maven
-   Docker
-   Docker Compose

##Installation and Start
### 1. Download
````
git clone git@github.com:GaloisGun/Food-Delivery-Application.git
cd Food-Delivery-Application/restaurant-service
````

### 2.  Run MongoDB Sever
> Configure MongoDB image version in `docker-compose.yml`.
```
docker-compose up -d
```

### 3.  Build and Run
````
mvn clean install
java jar ./target/restaurant-service-1.0.0.BUILD-SNAPSHOT.jar
````

### 4.  Upload Restaurant Information
>   Run the commond under `./restaurant-service`.
````
curl -H "Content-Type: application/json" localhost:8080/restaurantInfo -d @restaurantInfo.json
````

##API Overview
>   Check API References for detailed information

| Method | URL                   | Description                     | 
|--------|-----------------------|---------------------------------|
|POST    | /restaurantInfo       | Upload a list of RestaurantInfo |
|DELETE  | /restaurantInfo/purge | Delete all RestaurantInfo       |
|GET     | /restaurantInfo/{name}| Get one Restaurant by name      |


