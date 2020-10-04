# Building RESTful API using Spring MVC

It is a sample project to apply basic CRUD operations(Create, Read, Update, Delete) on a Car Repository using a controller and service layer.

### End Points Exposed:
- **Get:**
  - "/cars" : Get all the cars in the car repo.
  - "/cars/{cid}" : Get the car with car ID equal to cid.
- **Post:**
  - "/cars" : Save the car object in resquest body in car repo. 
- **Put:**
  - "/cars/{cid}" : Update the car with car ID equal to cid if it exists.
- **Delete:** 
  - "/cars/{cid}" : Delete the car with car ID equal to cid if it exists.
  
### Tools/Frameworks/Libraries used:

**Development:**
- Embedded Tomcat
- JPA Repository
- H2 Database
- Spring Tool Suite(IDE)
- Spring Boot
- Gradle

**Testing:**
- JUnit 5
- Mockito
- Postman 
  
