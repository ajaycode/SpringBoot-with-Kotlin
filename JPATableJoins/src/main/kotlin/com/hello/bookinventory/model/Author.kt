package com.hello.bookinventory.model

import javax.persistence.*

@Entity
data class Author (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var aid : Long = 0,

    var name : String = "",

    @OneToMany(mappedBy = "author", orphanRemoval = false)
    var bookList : List<Book> = mutableListOf()
){
    override fun toString(): String {
        return name
    }
}