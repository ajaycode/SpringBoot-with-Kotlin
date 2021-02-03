package com.hello.helloworld.securityinmemoryauth

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SecurityInMemoryApplication

fun main(args: Array<String>) {
	runApplication<SecurityInMemoryApplication>(*args)
}
