package com.hello.bookinventory.controller

import com.hello.bookinventory.model.Author
import com.hello.bookinventory.model.Book
import com.hello.bookinventory.service.BookService
import org.apache.logging.log4j.LogManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class BookController {

    @Autowired
    lateinit var bookService: BookService

    companion object {
        val logger = LogManager.getLogger(javaClass.enclosingClass)
    }

    //curl http://localhost:8080/addBook
    @RequestMapping ("/addBook")
    fun addBook (): String {
        val title = "Shakespeare"
        val author = "Arthur Conan Doyle"
        bookService.addBook(title, author)
        return "Book saved"
    }

    //curl http://localhost:8080/listBooks
    @RequestMapping ("/listBooks")
    fun listBooks (): String {
        val bookList : List<Book> = bookService.findAllBooks()
        var outputString : String = ""
        for (book in bookList)
        {
            val authorList : Author? = book.author
            if (authorList != null) {
                logger.info ("Title : ${book.title}, Author : ${authorList.name}")
                outputString += "[${book.title} by ${authorList.name}] "
            }
        }
        return outputString
    }

    @RequestMapping ("/deleteBook")
    fun deleteBook () : String {
        if (bookService.deleteBook ())
        {
            return "Book Deleted"
        }
        else {
            return "Book could not be deleted"
        }
    }
}