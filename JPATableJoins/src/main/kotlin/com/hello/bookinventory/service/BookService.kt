package com.hello.bookinventory.service

import com.hello.bookinventory.dao.AuthorRepository
import com.hello.bookinventory.dao.BookRepository
import com.hello.bookinventory.model.Author
import com.hello.bookinventory.model.Book
import org.apache.logging.log4j.LogManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import kotlin.random.Random
import kotlin.Triple as KotlinTriple

@Service
class BookService {

    @Autowired
    lateinit var authorRepository: AuthorRepository

    @Autowired
    lateinit var bookRepository: BookRepository

    companion object {
        val logger = LogManager.getLogger (javaClass.enclosingClass)
    }

    fun addBook (title : String, authorName: String) : Book {
        logger.info ("Adding a book $title and its author $authorName to repository ")
        val author : Author = Author(name = authorName)
        val book : Book = Book (title = title, author = author)

        authorRepository.save (author)
        val savedBook: Book = bookRepository.save (book)
        logger.info ("Saved book and its author to repository")
        return savedBook

    }

    fun findAllBooks () : List<Book> {
        val books = bookRepository.findAll()
        for (book in books){
            logger.info (book.title)
        }
        return books

    }

    fun deleteBook () : KotlinTriple<Boolean, Long, String> {
        val bookCount = bookRepository.count()
        val bookToDelete : Long = Random.nextLong(1, bookCount)
        logger.info ("Will attempt to delete book # $bookToDelete")
        if (bookRepository.existsById(bookToDelete))
        {
            val book  = bookRepository.findById(bookToDelete)
            var title : String = ""
            if (book.isPresent())
               title = book.get().title
            logger.info ("Title of book to be deleted : ${title}")
            var status : Boolean = true
            try {
                bookRepository.deleteById(bookToDelete)
            }
            catch (e: Exception)
            {
                logger.error("Unable to delete book # $bookToDelete, with title ${title} .")
                logger.error ("Exception thrown. ${e.message}")
                status = false
            }
            if (status == true)
                return KotlinTriple(true, second = bookToDelete, third = title)
        }
        return KotlinTriple(false, second = 0L, third = "null")
    }


    fun updateBook () : String {
        val bookCount = bookRepository.count()
        // var bookExists : Boolean = true
        var i : Long = 1
        while (!bookRepository.existsById(i) && i <= bookCount ){
            i++
            logger.info ("No book found with id # $i .")

        }
        if (i <= bookCount && bookRepository.existsById(i)){
            var book : Book = bookRepository.getOne(i)
            book.title = book.title + "[Updated]"
            var savedBook : Book = bookRepository.save(book)
            return ("Title was updated to ${savedBook.title}")
        }
        else {
            return ("Title was not updated")
        }



    }

    fun findAllAuthors () : List <Author> {
        return authorRepository.findAll()
    }

    fun findBooksByAuthor () : List <Book> {
        TODO ()
    }



}