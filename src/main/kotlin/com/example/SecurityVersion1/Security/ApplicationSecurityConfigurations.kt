package com.example.SecurityVersion1.Security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)

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
                ?.csrf()?.disable() //We need to understand it
                ?.authorizeRequests()

                //?.antMatchers(HttpMethod.DELETE,"/managment/**")?.hasAuthority(ApplicationUserPermission.STUDENT_WRITE.permission)
                //?.antMatchers(HttpMethod.POST,"/managment/**")?.hasAuthority(ApplicationUserPermission.STUDENT_WRITE.permission)
                //?.antMatchers(HttpMethod.PUT,"/managment/**")?.hasAuthority(ApplicationUserPermission.STUDENT_WRITE.permission)
                //?.antMatchers(HttpMethod.GET,"/managment/**")?.hasAnyRole(ApplicationUserRoles.ADMIN.name,ApplicationUserRoles.ADMINTRAINEE.name)

                ?.antMatchers("/api/**")?.hasRole(ApplicationUserRoles.STUDENT.name)
                ?.antMatchers("/","index","/css/*","/js/*")?.permitAll()
                ?.anyRequest()
                ?.authenticated()
                ?.and()
                ?.httpBasic()
    }

    @Bean
    override fun userDetailsService(): UserDetailsService {



        var annSmithUser = User.withUsername("annaSmith")
                .password(passwordEncoder.encode("password"))
                .authorities(ApplicationUserRoles.STUDENT.getGrantedAuthorities())
 //               .roles(ApplicationUserRoles.STUDENT.name)
                .build()
        //ROLE_STUDENT

        var lindaUser = User.withUsername("linda")
                .password(passwordEncoder.encode("password"))
                .authorities(ApplicationUserRoles.ADMIN.getGrantedAuthorities())
 //               .roles(ApplicationUserRoles.ADMIN.name)
                .build()
        //ROLE_ADMIN

        var tomUser = User.withUsername("tom")
                .password(passwordEncoder.encode("password"))
                .authorities(ApplicationUserRoles.ADMINTRAINEE.getGrantedAuthorities())
//                .roles(ApplicationUserRoles. ADMINTRAINEE.name)
                .build()
      //ROLE_ADMINTRAINEE


        val userDetailsManager = InMemoryUserDetailsManager()
        userDetailsManager.createUser(annSmithUser)
        userDetailsManager.createUser(lindaUser)
        userDetailsManager.createUser(tomUser)

        return userDetailsManager
    }
}