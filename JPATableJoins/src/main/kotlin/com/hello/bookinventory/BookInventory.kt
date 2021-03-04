package com.hello.bookinventory

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BookInventory

fun main(args: Array<String>) {
	runApplication<BookInventory>(*args)
}
