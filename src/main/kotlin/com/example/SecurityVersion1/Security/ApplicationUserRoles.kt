package com.example.SecurityVersion1.Security

import com.google.common.collect.Sets

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.util.stream.Collectors

enum class ApplicationUserRoles {
    STUDENT(Sets.newHashSet()),
    ADMIN(
            Sets.newHashSet(
             ApplicationUserPermission.COURSE_READ
            ,ApplicationUserPermission.COURSE_WRITE
            ,ApplicationUserPermission.STUDENT_READ
            ,ApplicationUserPermission.STUDENT_WRITE)
        ),
    ADMINTRAINEE(
    Sets.newHashSet(
             ApplicationUserPermission.COURSE_READ
            ,ApplicationUserPermission.STUDENT_READ
                )
    );

    var permission:Set<ApplicationUserPermission>
        public get() = field
        private set(value) {
            field = value
        }

    constructor(permission:Set<ApplicationUserPermission>){
        this.permission = permission
    }

    fun getGrantedAuthorities():Set<GrantedAuthority>{
      var   _permissions:MutableSet<SimpleGrantedAuthority>  = permission.stream()
               .map{SimpleGrantedAuthority (it.permission)}
              // .collect(Collectors.toSet())
              .collect(Collectors.toSet())
        _permissions.add(SimpleGrantedAuthority("ROLE_" + this.name))
        return _permissions
    }
}