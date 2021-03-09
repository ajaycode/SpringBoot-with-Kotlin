package com.hello.helloworld

import org.apache.logging.log4j.LogManager
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import java.time.LocalDate.now
import java.time.LocalDateTime
import kotlin.math.abs

@Controller
class HomeController {


    companion object {
        val logger = LogManager.getLogger()
    }

    @RequestMapping("/home")
    fun displayMessage(model: Model): String {
    model.addAttribute("time", LocalDateTime.now())
        return "home"
    }


    @RequestMapping("/subtract")
    //@ResponseBody
    //http://localhost:8080/subtract?num1=7&num2=9
    fun subtraction (@RequestParam(value = "num1", defaultValue = "7") num1: String, @RequestParam (value = "num2", defaultValue = "5") num2: String, model: Model): String{
        model.addAttribute("difference", abs(num1.toInt() - num2.toInt()))
        return "subtract"
    }

}