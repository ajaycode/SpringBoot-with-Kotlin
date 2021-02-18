package com.hello.helloworld

import com.hello.helloworld.model.User
import java.util.*

interface UserService {
    fun addUser(user: User): User
    fun getUsersByFirstNameIgnoreCase(first_name: String): Iterable<User>
    fun deleteUser(userid: Long) : Boolean
    fun updateUser(userid: Long, user: User): User
    fun updatePassword(userid: Long, newPassword: String): Boolean
    fun existsByUser_Id (userid: Long) : Boolean
    fun getUserById (id: Long) : User
}