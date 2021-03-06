package com.hello.bookinventory.model

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.util.*
import javax.persistence.*

@Entity
data class Book(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    var title: String = "",

    @OneToOne(orphanRemoval = false)
    @JoinColumn(name = "aid")
    val author: Author? = null,

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    var createdDate: Date? = null,

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    var modifiedDate: Date? = null
) {
    override fun toString(): String {
        return title
    }


}