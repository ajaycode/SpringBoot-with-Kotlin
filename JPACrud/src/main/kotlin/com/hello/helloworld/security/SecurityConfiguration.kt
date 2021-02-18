package com.hello.helloworld.security

import org.apache.logging.log4j.LogManager
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder


@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {

    private val ENCODED_PASSWORD =
        "$2a$10\$AIUufK8g6EFhBcumRRV2L.AQNz3Bjp7oDQVFiO5JJMBFZQ6x2/R/2"  // above stands for secret123


    companion object {
        val logger = LogManager.getLogger()
    }

    override fun configure(http: HttpSecurity) {
        if (http == null)
            logger.error("http is null")
        else {

            http.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/", "/register", "/reset_password", "/fonts/**", "/images/**", "/css/**", "/js/**", "/loaddb", "/user/**", "/employees_in_table", "/addition")
                .permitAll()
                .antMatchers(HttpMethod.GET, "/employees").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/multiplicationform").hasRole("ADMIN")
                .anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll()
                .and().logout().permitAll()
                .and().exceptionHandling().accessDeniedPage("/403")
        }
    }

    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.inMemoryAuthentication()
            .passwordEncoder(passwordEncoder())
            .withUser("user").password(ENCODED_PASSWORD).roles("USER")
            .and()
            .withUser("admin")
            .password(passwordEncoder().encode("pass"))
            .roles("ADMIN")
    }


    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }


}