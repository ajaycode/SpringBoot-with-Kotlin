package com.hello.bookinventory.entities

import javax.persistence.*

@Entity
@Table(name="products")
data class Product (
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    val id : Int = 0,
    val name : String = "",
    val price : Double = 0.0
)