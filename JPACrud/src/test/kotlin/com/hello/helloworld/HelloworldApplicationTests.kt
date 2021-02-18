package com.hello.helloworld

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.ResponseEntity

@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelloworldApplicationTests (@Autowired val restTemplate: TestRestTemplate) {

    @Test
    fun contextLoads() {
    }

    @Test
    fun `Assert that UserController-user-home returns the right value`() {
        val entity = restTemplate.getForEntity<String>("/user/home")
        println(entity.body)
        assertThat (entity.body).contains ("In /user/home")
    }

    @Test
    fun `addition`() {
        val entity = restTemplate.getForEntity<String>("/addition?num1=7&number=9")
        assertThat (entity.body).contains ("16")
        assert(entity.body.equals("16"))
    }
    
    @Test
    fun `Test HomeController-displayName with spring security` () {
        var result : ResponseEntity<String> = restTemplate.withBasicAuth("user", "secret123")
            .getForEntity<String>("/name/John")
        assertThat(result.body).contains("The string provided in the URL was : " + "John")
    }

}
