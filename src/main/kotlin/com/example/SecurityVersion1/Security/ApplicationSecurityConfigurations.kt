package com.example.SecurityVersion1.Security

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
@EnableWebSecurity
class ApplicationSecurityConfigurations :WebSecurityConfigurerAdapter(){
    override fun configure(http: HttpSecurity?) {
        http
                ?.authorizeRequests()
                ?.anyRequest()
                ?.authenticated()
                ?.and()
                ?.httpBasic()
    }
}