package com.hello.helloworld.repository

import com.hello.helloworld.model.Employee
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EmployeeRepository : JpaRepository<Employee, Long> {
    //fun getEmployeesById (eid: Long): Employee
    fun findByDepartment(name: String): List<Employee>
}