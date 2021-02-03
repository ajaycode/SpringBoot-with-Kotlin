package com.hello.helloworld.securityinmemoryauth

import org.apache.logging.log4j.LogManager
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import kotlin.jvm.Throws


@EnableWebSecurity
class WebSecurityConfiguration : WebSecurityConfigurerAdapter (){

    private val ENCODED_PASSWORD = "\$2y\$12\$/MGnT4p1h6/IGkXN9bRwjutaqXIX/KpJo7ZQwyBXjto2cFuKPWbnW"  // hash of "user"

    companion object {
        val logger = LogManager.getLogger()
    }

    //Authentication
    @Throws (Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder) {
        logger.info ("In Authentication")

        auth.inMemoryAuthentication()
            .passwordEncoder(passwordEncoder())
            .withUser("user").password(ENCODED_PASSWORD).roles("USER")
            .and()
            .withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN")
    }

    @Throws (Exception::class)
    override fun configure(http: HttpSecurity) {
        logger.info ("In Authorization")

        http
            .authorizeRequests()
            .antMatchers ("/admin").hasRole ("ADMIN")
            .antMatchers ("/user").hasRole ("USER")
            .antMatchers ("/all").hasAnyRole ("ADMIN", "USER")
            .antMatchers ("static/css", "static/js").permitAll()
            .and().formLogin().permitAll()
            .and().logout().permitAll()
            .and().exceptionHandling().accessDeniedPage("/403")
    }

    @Bean
    fun passwordEncoder (): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}