package com.hello.helloworld.securityinmemoryauth

import org.apache.logging.log4j.LogManager
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class HomeController {

    companion object {
        val logger = LogManager.getLogger()
    }

    @GetMapping("/")
    @ResponseBody
    fun index (): String {
        return ("<h1>You are on the index page</h1>")
    }

    @RequestMapping ("/user")
    @ResponseBody
    fun user (): String {
        logger.info ("In user method.")
        return ("<h1>You are on the user page</h1>")
    }

    @RequestMapping ("/admin")
    @ResponseBody
    fun admin (): String {
        logger.info ("In admin method.")
        return ("<h1>You are on the admin page</h1><a href=\"login?logout\">Logout</a>")
    }

    @RequestMapping ("/all")
    fun all (): String {
        logger.info ("In all method.")
        return "all"
    }

    @GetMapping ("/403")
    @ResponseBody
    fun accessDenied (): String {
        logger.error ("403 error")
        return ("<h1>403 Error: Access forbidden.</h1>")
    }

    @RequestMapping ("/login")
    fun login ():String {
        return "login"
    }
}