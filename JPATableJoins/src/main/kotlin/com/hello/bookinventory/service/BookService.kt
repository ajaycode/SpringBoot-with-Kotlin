package com.hello.bookinventory.service

import com.hello.bookinventory.dao.AuthorRepository
import com.hello.bookinventory.dao.BookRepository
import com.hello.bookinventory.model.Author
import com.hello.bookinventory.model.Book
import org.apache.logging.log4j.LogManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import kotlin.random.Random

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

    fun deleteBook () : Boolean {
        val bookCount = bookRepository.count()
        val bookToDelete : Long = Random.nextLong(0, bookCount -1)
        logger.info ("Will attempt to delete book # $bookToDelete")
        if (bookRepository.existsById(bookToDelete))
        {
            bookRepository.deleteById(bookToDelete)
        }
        return  !(bookRepository.existsById(bookToDelete))
    }

    fun findAllAuthors () : List <Author> {
        return authorRepository.findAll()
    }

    fun findBooksByAuthor () : List <Book> {
        TODO ()
    }



}