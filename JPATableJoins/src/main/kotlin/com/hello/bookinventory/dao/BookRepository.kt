package com.hello.bookinventory.dao

import com.hello.bookinventory.entities.Product
import com.hello.bookinventory.model.Book
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository : JpaRepository <Book, Long> {
}