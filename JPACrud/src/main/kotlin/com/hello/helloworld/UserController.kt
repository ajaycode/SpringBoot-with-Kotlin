package com.hello.helloworld

import com.hello.helloworld.model.User
import org.apache.logging.log4j.LogManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import java.time.LocalDate.now
import java.time.LocalDateTime
import java.util.*


@Controller
class UserController {

    @Autowired
    lateinit var userService: UserService

    companion object {
        val logger = LogManager.getLogger()
    }

    @RequestMapping("/user/home")
    @ResponseBody
    fun displayMessage(): String {
        return "In /user/home"
    }

    //http://localhost:8080/user/adduser
    @RequestMapping("/user/adduserform")
    fun displayUserForm(model: Model): String {
        model.addAttribute("user", User())
        model.addAttribute("HeaderText", "Add a user:")
        return "adduserform"
    }

    //displays the confirmation that the user was added
    @RequestMapping("/user/adduser", method = [(RequestMethod.POST)])
    fun addUser(@ModelAttribute("user") user: User, result: BindingResult, model: Model): String {
        //model.addAttribute("numbers", MyNumbers () )
        logger.info(user)

        if (result.hasErrors()) {
            logger.error("Binding error occurred.  User could not be added.  Please retry.")
            return "adduserform"
        } else {
            // var user: User = User(numbers.multiplicand, numbers.multiplier)
            userService.addUser(user)
            model.addAttribute("datetime", now())
            model.addAttribute("user", user)

            //logger.info(numbers.product)
            return "addeduser"
        }
    }


    //http://localhost:8080/user/delete/9
    //http://localhost:8080/user/delete/4
    @GetMapping("/user/delete/{id}")
    @ResponseBody
    fun deleteUser(@PathVariable("id", required = true) id: Long): String {
        UserController.logger.info("Requesting deletion of userId = " + id)
        var status: Boolean = true
        if (id < 0)
            status = false
        else {
            status = userService.deleteUser(id)
        }
        val outputString: String = if (status) "User $id was deleted" else "User $id was not deleted."
        return outputString
    }


    //http://localhost:8080/user/search/john
    @GetMapping("/user/search/{firstName}")
    @ResponseBody
    fun searchByFirstName(@PathVariable("firstName", required = true) firstName: String): String {

        UserController.logger.info("Will search for users with a first name of  = $firstName (case insensitive search)")
        var message: String = ""

        var users: List<User>
        if (firstName.length <= 0)
            return "Invalid firstName $firstName provided to searchByFirstName."
        else {
            val users: List<User> = userService.getUsersByFirstNameIgnoreCase(firstName) as List<User>
            return users.toString()

        }
    }

    //http://localhost:8080/user/edit/4 -> Will display the edit user form
    @GetMapping("/user/edit/{id}")
    fun editUserForm(@PathVariable("id", required = true) id: Long, model: Model): String {
        if (id >= 0) {
            if (userService.existsByUser_Id(id) == true) {
                UserController.logger.info ("User with userid of $id exists in the database.")
                var user : User = userService.getUserById(id)
                model.addAttribute("user", user)
                model.addAttribute("HeaderText", "Add a user:")
                return "edituserform"
            } else {
                return "error"
            }

        } else {
            return "error"
        }
    }

    @PostMapping("/user/updateuser")
    @ResponseBody
    fun updateUser (@ModelAttribute("user") user: User, result: BindingResult, model: Model): String {
        if (user == null)
            return "error"
        logger.info ("To update user # ${user.userId} with values : $user")
        userService.updateUser(user.userId, user)
        return "User ${user.userId} was updated."
    }


}


