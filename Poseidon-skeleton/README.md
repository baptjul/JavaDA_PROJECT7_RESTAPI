# spring-boot
## Technical:

1. Framework: Spring Boot v2.7.5
2. Java 1.8
3. MySql: 8.0.31
4. Thymeleaf
5. Bootstrap v.4.3.1

## Setup with Intellij IDE
1. Create project from Initializr: File > New > project > Spring Initializr
2. Add lib repository into pom.xml
3. Create the folders in the project:
    - Source root: src/main/java
    - View: src/main/resources
    - Static: src/main/resource/static
4. Configure the database by adding the line to application.properties:
    - spring.datasource.url=jdbc:mysql://localhost:3306/demo?useSSL=false

Make sure to replace localhost with your MySQL server hostname and 3306 with your MySQL server port

5. Run sql script to create table doc/data.sql
    - basic user : 
	- username: user / password: password
    - admin user :
	- username: admin / password: password

## Implement a Feature
1. Create mapping domain class and place in package com.nnk.springboot.domain
2. Create repository class and place in package com.nnk.springboot.repositories
3. Create controller class and place in package com.nnk.springboot.controllers
4. Create view files and place in src/main/resource/templates

## Unit Test
1. Unit test are placed in package com.nnk.springboot in folder test > java

## Security
1. configuration class is placed in package com.nnk.springboot.configuration
