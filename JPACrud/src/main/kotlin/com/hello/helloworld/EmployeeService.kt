package com.hello.helloworld

import com.hello.helloworld.model.Employee

interface EmployeeService {
    fun addEmployee(employee: Employee): Employee
    fun deleteEmployee(eid: Long): String
    fun loadEmployees(): String
    fun getEmployees(): MutableIterable<Employee>?
    fun findEmployeesByDepartment(department: String): List<Employee>
    fun updateEmployee (eid: Long, employee: Employee) : String


}