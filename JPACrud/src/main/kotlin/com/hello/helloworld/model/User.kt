package com.hello.helloworld.model

import org.hibernate.validator.constraints.Length
import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size


@Entity
@Table(name = "User")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //.IDENTITY prevents conflicts when you may input data using a script and then use hibernate.  Hibernate may try to insert a new entry using an existing key
    @Column(nullable = false, name = "User_id")
    val userId: Long = 0,

    @Column(nullable = false, name = "First_Name")
    @Length(min = 2)
    var firstName: String = "",

    @Column(nullable = false, name = "Last_Name")
    @Length(min = 2)
    @NotBlank(message = "Last name must contain two or more characters.")
    val lastName: String = "",

    @Column(nullable = false, unique = true, name = "Username")
    @Length(min = 5)
    val username: String = "",

    @Column(nullable = false, unique = true, name = "Email")
    @Email(message = "Please provide a valid email address.")
    val email: String = "",

    @Column(nullable = false, name = "Password")
    @Size(min = 4)
    val password: String = "",

    @Column(name = "Create_Time")
    var createTime: LocalDateTime = LocalDateTime.now(),

    @Column(name = "Modify_Time")
    var modifyTime: LocalDateTime? = null
) {

    // Other JPA lifecycle events available at https://www.baeldung.com/jpa-entity-lifecycle-events
    @PrePersist
    fun prePersist() {
        //createTime = LocalDateTime.now()
    }

    @PreUpdate
    fun preUpdate() {
        modifyTime = LocalDateTime.now()
    }
}


