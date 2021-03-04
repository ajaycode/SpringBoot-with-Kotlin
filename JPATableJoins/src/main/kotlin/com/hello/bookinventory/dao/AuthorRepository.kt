package com.hello.bookinventory.dao

import com.hello.bookinventory.model.Author
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface  AuthorRepository : JpaRepository <Author, Long> {

    /*@Query("SELECT a FROM Author a JOIN FETCH a.book where a.name = ?1")
    fun getBookByName (bookName : String) : Author*/
}