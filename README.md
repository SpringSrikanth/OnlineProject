# Spring REST CRUD API with Spring Boot, Mysql, JPA ,Spring Security, JWT, Mongodb, Hibernate 

## Steps to Setup

**1. Clone the application**

```bash
https://github.com/SpringSrikanth/OnlineProject.git
branch master
```

**2. Create Mysql database**
```bash
create database mydb
```

**3. Change mysql username and password as per your installation**

+ open `src/main/resources/application.properties`

+ change `spring.datasource.username` and `spring.datasource.password` as per your mysql installation

to see <a href="https://github.com/SpringSrikanth/OnlineProject/blob/master/pom.xml">pom.xml</a>

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

**UserDetails Controller <a href="https://github.com/SpringSrikanth/OnlineProject/blob/master/src/main/java/com/koseksi/pachipulusula/controller/UserDetailsController.java">view</a>

**Root path "users"
 eg:"users/create/user"

   POST Create User= "/create/user"
  
   POST Update User="/user/update"
  
   GET Users Working or not = "/health"
  
   POST Delete User="/user/delete/{id}" eg: "users/user/delete/1"
  
   GET User Details By Id="/user/{id}"


**MainController (Auth Controller) <a href="https://github.com/SpringSrikanth/OnlineProject/blob/master/src/main/java/com/jwt/jwtProject/MainController.java">view</a>

**Root path ""

+ POST "/authenticate"
+ GET "/welcome"


**UsersRoles Controller <a href="https://github.com/SpringSrikanth/OnlineProject/blob/master/src/main/java/com/koseksi/pachipulusula/controller/UsersRolesController.java">view</a>

**Root path ""

+ POST "/role/create"
+ POST "/addRole/ToUsers/{roleId}"
+ POST "/userRole/removeUser" 
+ POST "/addRole/removeAllUsers" 
+ POST "/deleteRole"


**JpaModal Controller <a href="https://github.com/SpringSrikanth/OnlineProject/blob/master/src/main/java/com/koseksi/pachipulusula/controller/JpaModalController.java">view</a>

**Root path ""

+ GET "/user/users"
+ GET "/user/roles


**Common Controller <a href="https://github.com/SpringSrikanth/OnlineProject/blob/master/src/main/java/com/koseksi/pachipulusula/controller/CommonController.java">view</a>

**Root path ""

+ POST "/encode/encodeString/{text}"
+ POST "/decode/decodeString/{text}"


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
    
