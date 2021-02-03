# Read Me First
The following was discovered as part of building this project:


# Getting Started

### Spring Boot Security
This is an example of Spring Boot Security with a custom login page. It contains
* authentication
* authorization
* custom login page

There are two users as follows:
* "user" with a password of "user"
* "admin" with a password of "admin"

The password for "user" is hashed using bcrypt-generator.com and hard coded into WebSecurityConfiguration.kt
The password for "admin" is hashed using BcryptPasswordEncoder().

Logout capability is available by adding the "logout" keyword to the login URL as below
http://localhost:8080/login?**logout**
Logout is available in 
* /all - Implemented in html
* /admin page - embedded into the return string of admin () method

Access to pages is as follows:
* "user" has access to /user
* "admin" has access to /admin
* /all is accessible to authenticated users - "user" and "admin"
* / is accessible without authentication

To try it out:
* access http://localhost:8080/user and login as "user"/"user"
* logout at http://localhost:8080/login?logout 
* access http://localhost:8080/admin and login as "admin"/"admin"
* access http://localhost:8080/all as either "user" or "admin"


### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.2/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.4.2/gradle-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.4.2/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Security](https://docs.spring.io/spring-boot/docs/2.4.2/reference/htmlsingle/#boot-features-security)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.4.2/reference/htmlsingle/#boot-features-jpa-and-spring-data)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

