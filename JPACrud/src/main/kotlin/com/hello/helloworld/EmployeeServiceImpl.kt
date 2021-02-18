package com.hello.helloworld

import com.hello.helloworld.EmployeeService
import com.hello.helloworld.model.Employee
import com.hello.helloworld.repository.EmployeeRepository
import org.apache.logging.log4j.LogManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*


@Service
class EmployeeServiceImpl : EmployeeService {
    @Autowired
    lateinit var employeeRepository: EmployeeRepository

    companion object {
        val logger = LogManager.getLogger(javaClass.enclosingClass)
    }



    override fun addEmployee(employee: Employee): Employee {
        TODO("Not yet implemented")
    }

    override fun getEmployees ():MutableIterable<Employee>? = employeeRepository.findAll()

    /* override fun deleteEmployee(eid: Long): String {
        TODO("Not yet implemented")
    } */

    //fun getAllEmployees () = employeeRepository.findAll()


    override fun loadEmployees (): String {
        logger.info ("In loadEmployees")

        employeeRepository.save (Employee (name = "John", department = "Finance"))
        employeeRepository.save (Employee (name = "Jake", department = "Marketing"))
        employeeRepository.save (Employee (name = "Jane", department = "Sales"))
        employeeRepository.save (Employee (name = "Jill", department = "HR"))
        employeeRepository.save (Employee (name = "Jim", department = "Finance"))
        employeeRepository.save (Employee (name = "Jose", department = "Administration"))
        return "Loaded employees into database"
    }


    override fun updateEmployee(eid: Long, employee: Employee): String {
        logger.info ("Updating employee # ", eid)
        val currentEmployee  = employeeRepository.findById(eid)
        if (currentEmployee != null)
        {
            employeeRepository.save (Employee (eid, employee.name, employee.department))
            logger.info ("Updated employee record.")
        }
        return ("Employee record updated.")
    }

    override fun deleteEmployee(eid: Long): String {
        logger.info ("Deleting employee # " + eid)
        employeeRepository.deleteById(eid)
        return "Employee removed from database"
    }

    override fun findEmployeesByDepartment (department: String) : List <Employee> {
        return employeeRepository.findByDepartment(department)
    }
}