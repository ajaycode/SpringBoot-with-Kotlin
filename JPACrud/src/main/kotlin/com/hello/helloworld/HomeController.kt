package com.hello.helloworld

import org.apache.logging.log4j.LogManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import com.hello.helloworld.model.Employee
import com.hello.helloworld.EmployeeService
import org.aspectj.weaver.ArrayReferenceType
import org.springframework.boot.web.servlet.server.Session
import org.springframework.web.servlet.ModelAndView
import java.time.LocalDate.now
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpSession
import kotlin.math.abs


//TODO: Joins on tables
//TODO: Spring security -> https://medium.com/techwasti/enable-spring-security-using-kotlin-6b9abb36d218 or
//                         https://www.baeldung.com/kotlin/spring-security-dsl or
//                         https://medium.com/@iamdeepaksp/spring-security-for-kotlin-4b8fb5e7ed1e
//TODO: JWT -> https://github.com/Java-Techie-jt/spring-security-jwt-example/tree/master/src/main/java/com/javatechie/jwt/api
//TODO: Pagination of results -> http://zetcode.com/springboot/pagination/
//TODO: Web pages with sessions
//       1-> https://www.techgeeknext.com/spring-boot/spring-boot-session-management
//       2-> https://www.javainuse.com/spring/springboot_session
//       3-> https://www.baeldung.com/spring-session
//       4-> https://www.javadevjournal.com/spring/spring-session-with-jdbc/
//TODO: Unit tests -> https://github.com/kousen/spring-kotlin/blob/master/demo/src/test/kotlin/com/kousenit/demo/controllers/HelloControllerUnitTests.kt
//TODO: Unit tests (Kotlin approach) -> https://github.com/kousen/spring-kotlin/blob/master/demo/src/test/kotlin/com/kousenit/demo/controllers/HelloControllerMockMVCTests.kt
//TODO: Secure connection to databases
//TODO: CORS for object storage
//TODO: Deploy to AWS Elastic Bean Stalk
//TODO: Form validation and display of results to the user -> https://spring.io/guides/gs/validating-form-input/
//TODO: JQuery: Ajax - drop down values for a text field
//TODO: Ajax - Update page without full refresh
//TODO: Session affinity when behind a load balancer (Is JWT required?)
//TODO: One to Many Mapping, with self-join -> https://www.viralpatel.net/hibernate-self-join-annotations-one-to-many-mapping/
//TODO: HTTPS
//TODO: Review other capabilities at http://www.kousenit.com/springbootkotlin/
//TODO: Form validation: https://roytuts.com/form-validation-in-spring-boot-web-application/
//Full application -> https://github.com/spring-petclinic/spring-petclinic-kotlin/tree/master/src/main/resources/templates
//Misc - Connecting to multiple data sources, CORS, NoSQL, logging, micro services, messaging, Kafka, send email, websocket, JWT, social media connectivity -> https://grokonez.com/spring-framework-tutorial/spring-boot
//Run from command line: gradlew.bat bootRun

//gradlew clean build -x test (do a clean build, skip tests)


@Controller
class HomeController {

    @Autowired
    lateinit var employeeService : EmployeeService

    companion object {
        val logger = LogManager.getLogger()
    }

    @RequestMapping("/home")
    @ResponseBody
    fun displayMessage(): String {
        return "In /home"
    }

    /*
    @GetMapping ("/sessiontest")
    @ResponseBody
    fun sessionintro (model: Model, session: HttpSession): String {
        var messages = mutableListOf<String> (session.getAttribute("MY_MESSAGES").toString())
        if (messages == null) messages =  mutableListOf ("")
        model.addAttribute("sessionMessages", messages)
        return "sessiontest"
    }

    @PostMapping ("/persistmessage")
    fun persistmessage(@RequestParam ("msg") msg:String, request:HttpServletRequest) : String {
        var messages = mutableListOf<String> (request.getSession().getAttribute("MY_MESSAGES").toString())
        if (messages == null) {
            messages = mutableListOf("")
            request.getSession().setAttribute("MY_MESSAGES", messages)

        }
        logger.info ("session message : " + messages.toString())
        messages.add(msg)
        //messages = messages + msg
        request.getSession().setAttribute("MY_MESSAGES", messages)
        return "sessiontest"
    }

    @GetMapping ("/changeusername")
    fun setcookie(response: HttpServletResponse) : String {
        var cookie : Cookie = Cookie("username", "John")
        response.addCookie(cookie)
        return "username was changed."

    }

    @GetMapping ("allcookies")
    fun getallcookies (request: HttpServletRequest) : String {
        var cookies  = arrayOf<Cookie!>(request.cookies )
        logger.info ("Cookies found")

    }

     */

    //Get a value from the URL, using PathVariable.  PathVariable is used to extract data from URI
    //http://localhost:8080/name/Rome
    @GetMapping("/name/{name}")
    @ResponseBody
    fun displayName(@PathVariable("name", required = true) name: String): String {

        val userValue : String = if (name.length != 0) name else  "No string was provided"
        //TODO: can this be rewritten as userValue? "No string was provided"
        logger.info ("userValue = " +  userValue)
        val outputString: String = "The string provided in the URL was : " + userValue
        return outputString
    }

    //Get multiple input values from the URL, using RequestParam.  RequestParam is used to extract data found in query parameters
    @RequestMapping("/addition")
    @ResponseBody
    //http://localhost:8080/addition?num1=7&number=9
    fun addition(@RequestParam(value = "num1", defaultValue = "5") num1: String, @RequestParam(value = "number", defaultValue = "5") num2: String): Int {
        return num1.toInt() + num2.toInt()
    }

    @RequestMapping("/subtract")
    //@ResponseBody
    //http://localhost:8080/subtract?num1=7&num2=9
    //fun subtraction(model: Model): String {
    fun subtraction (@RequestParam(value = "num1", defaultValue = "7") num1: String, @RequestParam (value = "num2", defaultValue = "5") num2: String, model: Model): String{
        model.addAttribute("difference", abs(num1.toInt() - num2.toInt()))
        //model.addAttribute("difference", "7")
        return "subtract"
    }


    @RequestMapping("/multiply", method = [(RequestMethod.POST)])
    fun multiply(@ModelAttribute("numbers") numbers: MyNumbers, result: BindingResult, model: Model): String {
        //model.addAttribute("numbers", MyNumbers () )
        logger.info(numbers)

        if (result.hasErrors()) {
            logger.error("Binding error occurred")
            return "multiplicationform"
        } else {
            var nums: MyNumbers = MyNumbers(numbers.multiplicand, numbers.multiplier)
            logger.info("multiplicand = " + numbers.multiplicand)
            logger.info("multiplier = " + numbers.multiplier)//, numbers.multiplier)
            logger.info("product = " + numbers.product)
            model.addAttribute("datetime",  now())
            model.addAttribute("numbers", nums)
            model.addAttribute("numbers.product", numbers.product)
            //logger.info(numbers.product)
            return "multiply"
        }
    }

    //http://localhost:8080/multiplicationform
    @RequestMapping("/multiplicationform")
    fun displayMultiplicationForm(model: Model): String {
        model.addAttribute("numbers", MyNumbers())
        model.addAttribute("HeaderText", "Enter the values in the form below:")
        return "multiplicationform"
    }


    @RequestMapping("/loaddb")
    @ResponseBody
    fun loadEmployees (): String {
        employeeService.loadEmployees()
        return ("Loaded employees into database")
    }

    @GetMapping ("/employees")
    @ResponseBody
    fun getAllEmployees () = employeeService.getEmployees ()

    //http://localhost:8080/loaddb -> To populate the database
    //http://localhost:8080/employees_in_table -> To view the list of employees
    @GetMapping ("/employees_in_table")
    fun getEmployeesInTable ():ModelAndView {
        var employees : List<Employee>? = employeeService.getEmployees()?.toList()
        //logger.info ("List of employees : " + employees)
        var mv : ModelAndView = ModelAndView()
        mv.addObject("employees", employees)
        mv.addObject("Title", "Displays the list of employees in a table using Thymeleaf th:each syntax:")
        mv.viewName = "employees_in_table"
        return mv
    }

    //http://localhost:8080/department/Finance -> returns list of employees from "Finance" department
    @GetMapping("/department/{departmentName}")
    @ResponseBody
    fun getEmployeesByDepartment (@PathVariable ("departmentName")  departmentName: String) : List <Employee> {

        return employeeService.findEmployeesByDepartment(departmentName)
    }

    //will get the following, if only RequestMethod.DELETE or @DeleteMapping is used. => org.springframework.web.HttpRequestMethodNotSupportedException: Request method 'GET' not supported
    @RequestMapping ("/employeedelete/{eid}", method = arrayOf(RequestMethod.DELETE, RequestMethod.GET))
    @ResponseBody
    fun deleteEmployee (@PathVariable("eid") eid: Long) : String {
        logger.info ("Employee to delete : " + eid)
        employeeService.deleteEmployee(eid)
        logger.info ("Employee # " + eid + " was deleted in the repository.")
        return ("Employee was deleted from repository")
    }

    //http://localhost:8080/employees => to see the list of employees
    //http://localhost:8080/employeeupdate/1/Jeff/Administration => to update  the employee with eid = 1 with the new name and department
    @RequestMapping ("/employeeupdate/{eid}/{name}/{department}", method = arrayOf(RequestMethod.PUT, RequestMethod.GET))
    @ResponseBody
    fun updateEmployee (@PathVariable("eid") eid: Long,
                        @PathVariable ("name") name: String,
                        @PathVariable ("department") department: String) : ModelAndView {
        logger.info ("Updating employee # " + eid)
        var mv : ModelAndView = ModelAndView()
        mv.addObject("Employee", Employee(eid = eid, name = name, department = department))
        employeeService.updateEmployee(eid, Employee(name = name, department = department))
        mv.viewName = "jumbotron"
        return  mv
    }

    @RequestMapping ("/login")
    fun login (): String {
        return "login"
    }

    @RequestMapping ("/403")
    fun error403():String{
        return "403"
    }
}