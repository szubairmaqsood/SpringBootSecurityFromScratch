package com.example.SecurityVersion1.Security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager


@Configuration
@EnableWebSecurity
class ApplicationSecurityConfigurations :WebSecurityConfigurerAdapter{
    private  val  passwordEncoder:PasswordEncoder


    @Autowired
    constructor(_passwordEncoder:PasswordEncoder){
        /*
          This will come from Password Configuration
         */

        this.passwordEncoder = _passwordEncoder
    }
    override fun configure(http: HttpSecurity?) {
        http
                ?.authorizeRequests()
                ?.antMatchers("/","index","/css/*","/js/*")
                ?.permitAll()
                ?.anyRequest()
                ?.authenticated()
                ?.and()
                ?.httpBasic()
    }

    @Bean
    override fun userDetailsService(): UserDetailsService {



        var annSmithUser = User.withUsername("annaSmith")
                .password(passwordEncoder.encode("password")).roles(ApplicationUserRoles.STUDENT.name).build()

        var lindaUser = User.withUsername("linda")
                .password(passwordEncoder.encode("password")).roles(ApplicationUserRoles.ADMIN.name).build()



        val userDetailsManager = InMemoryUserDetailsManager()
        userDetailsManager.createUser(annSmithUser)
        userDetailsManager.createUser(lindaUser)

        return userDetailsManager
    }
}