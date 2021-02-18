package com.hello.helloworld.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "Employee")
data class Employee (
        @Id
        @GeneratedValue
        val eid: Long = 0,
        val name: String = "",
        val department: String = "")