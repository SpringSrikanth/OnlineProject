# Spring REST CRUD API with Spring Boot, Mysql, JPA ,Spring Security, JWT, Mongodb, Hibernate 

## Steps to Setup

**1. Clone the application**

```bash
https://github.com/SpringSrikanth/OnlineProject.git
```

**2. Create Mysql database**
```bash
create database mydb
```

**3. Change mysql username and password as per your installation**

+ open `src/main/resources/application.properties`

+ change `spring.datasource.username` and `spring.datasource.password` as per your mysql installation

**4. Build and run the app using maven**

```bash
To build and download dependencies--> mvn clean install -DskipTests

To build .jar file --> mvn clean package -DskipTests

To run application --> java -jar target/spring-boot-rest-api-tutorial-0.0.1-SNAPSHOT.jar -DskipTests

Another method to run application --> mvn spring-boot:run -DskipTests

```

Alternatively, you can run the app without packaging it using -

```bash
mvn spring-boot:run -DskipTests
```

The app will start running at <http://localhost:9090>.

## Explore Rest APIs

The app defines following CRUD APIs.

    GET /api/v1/users
    
    POST /api/v1/users
    
    GET /api/v1/users/{userId}
    
    PUT /api/v1/users/{userId}
    
    DELETE /api/v1/users/{userId}


## Used Dependencies

    mysql-connector-java(5.1.28)
    
    h2database
    
    jjwt(9)
    
    jaxb-api
    
    commons-codec
    
    spring-boot-starter-data-mongodb
    
    spring-boot-devtools
    
    Spring Secutiry
    
    Log4j
    
## updated to MySQL 8.0 

    to download pls follow https://dev.mysql.com/downloads/file/?id=499590
    https://dev.mysql.com/get/Downloads/MySQLInstaller/mysql-installer-community-8.0.22.0.msi
    
