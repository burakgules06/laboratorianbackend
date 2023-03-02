# laboratorianbackend
## Technologies
- JAVA (JDK-17)
- PostgreSQL
- Hibernate ORM
- JPA
- Lombok
- Spring Framework--> Boot, MVC, DATA

## Postgresql properties

```
spring.datasource.url={PostgreSql Server URL}
spring.datasource.username={PostgreSql Server Username}
spring.datasource.password={PostgreSql Server Password}
```


## Setup
```
$ git clone https://github.com/burakgules06/laboratorianbackend.git
$ cd laboratorianbackend
$ sudo apt-get install maven
$ mvn -N io.takari:maven:wrapper
$ ./mvnw package
$ cd target/
$ java -jar laborantproject-0.0.1-SNAPSHOT.jar
