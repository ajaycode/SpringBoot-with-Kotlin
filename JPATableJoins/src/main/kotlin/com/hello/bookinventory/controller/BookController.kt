package com.hello.bookinventory.controller

import com.hello.bookinventory.model.Author
import com.hello.bookinventory.model.Book
import com.hello.bookinventory.service.BookService
import org.apache.logging.log4j.LogManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.Triple as KotlinTriple

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

    //curl http://localhost:8080/deleteBook
    // Deletes a random book from the book table
    @RequestMapping ("/deleteBook")
    fun deleteBook () : String {
        val returnTriple  =  bookService.deleteBook ()
        if (returnTriple.first == true)
        {
            return "Book ${returnTriple.second} with title ${returnTriple.third} Deleted"
        }
        else {
            return "Book could not be deleted"
        }
    }

    //curl http://localhost:8080/updateBook
    // Updates the title of the book from X to X[Updated].  The timestamp should also be updated.
    @RequestMapping("/updateBook")
    fun updateBook (): String {
        return bookService.updateBook()
    }
}