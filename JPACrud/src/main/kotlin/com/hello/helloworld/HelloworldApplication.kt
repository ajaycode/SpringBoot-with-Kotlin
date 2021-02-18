package com.hello.helloworld

/* TO DO
1. https://grokonez.com/spring-framework/spring-boot/kotlin-spring-boot/kotlin-springmvc-form-submission-thymeleaf-springboot-boostrap-4-form
2. Use ModelAttribute to get data from a form as a single object.
 */

import com.hello.helloworld.repository.EmployeeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.support.beans

@SpringBootApplication
@EnableConfigurationProperties     //Auto create tables in database
open class HelloworldApplication


@Autowired
lateinit var employeeRepository : EmployeeRepository

fun main(args: Array<String>) {
    runApplication<HelloworldApplication>(*args)
}
