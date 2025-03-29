# Shop Application

This is a simple Shop application built using Spring Boot. It provides functionalities for managing products, customers, and orders.

## Installation:

To get started with the application, you'll need to have Java 21 or later installed on your machine.

1. Download this application by executing the following command in your cmd:

`git clone https://github.com/Zdzimi/shop.git`

2. Rename file: `\shop\src\main\resources\application.properties.template` to: `\shop\src\main\resource\sapplication.properties` and fill in the configuration data according to the suggestions in the comments. Come back to your cmd and run following commands:

`cd shop/`

`mvn install`

`cd target/`

`java -jar shop-0.0.1-SNAPSHOT.jar`

3. Open browser, and go to: `localhost:8080/shop`. You can create new user or sign in as predefined admin.

## Dependencies:

- [Thymeleaf](https://www.thymeleaf.org/) - Template engine for the view layer.
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa) - Simple and efficient database integration using JPA (Java Persistence API).
- [Spring Security](https://spring.io/projects/spring-security) - Provides security features such as authentication, authorization, and session management.
- [Spring Validation](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-ann-form-validation) - Module for validating input data in the controller layer.
- [Liquibase](https://www.liquibase.org/) - A tool for controlling and managing the version of the database structure.
- [Lombok](https://projectlombok.org/) - A library that makes writing code easier by automatically generating methods such as getters, setters, etc.
- [Hateoas](https://spring.io/projects/spring-hateoas) - The core problem it tries to address is link creation and representation assembly.
