package com.example.SecurityVersion1.Security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class PasswordConfigurations {

    @Bean
    fun passwordEncoder():PasswordEncoder{
        return BCryptPasswordEncoder(10)
    }
}