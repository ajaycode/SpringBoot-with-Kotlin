package com.hello.bookinventory.model

import javax.persistence.*

@Entity
data class Book(
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    var id : Long = 0,

    var title: String = "",

    @OneToOne(cascade = arrayOf(CascadeType.ALL))
    @JoinColumn(name = "aid")
    var author : Author? = null
) {
    override fun toString(): String {
        return title
    }
}