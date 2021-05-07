package com.example.SecurityVersion1.Security

import com.google.common.collect.Sets

enum class ApplicationUserRoles {
    STUDENT(Sets.newHashSet()),
    ADMIN(
            Sets.newHashSet(
             ApplicationUserPermission.COURSE_READ
            ,ApplicationUserPermission.COURSE_WRITE
            ,ApplicationUserPermission.STUDENT_READ
            ,ApplicationUserPermission.STUDENT_WRITE)
        );

    var permission:Set<ApplicationUserPermission>
        public get() = field
        private set(value) {
            field = value
        }

    constructor(permission:Set<ApplicationUserPermission>){
        this.permission = permission
    }
}