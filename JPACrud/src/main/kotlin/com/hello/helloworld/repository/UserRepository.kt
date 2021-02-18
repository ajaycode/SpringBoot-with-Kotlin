package com.hello.helloworld.repository

import com.hello.helloworld.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository <User, Long> {
    //fun findByEmail (name: String) : List <User>

    fun findByFirstNameIgnoreCase (name:String) : List <User>
    fun findByFirstName (name:String) : List <User>
}