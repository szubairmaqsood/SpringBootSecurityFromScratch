package com.example.SecurityVersion1.Security

enum class ApplicationUserPermission {
    STUDENT_READ("student:read"),
    STUDENT_WRITE("student:write"),
    COURSE_READ("course:read"),
    COURSE_WRITE("course:write");
    var permission:String
     public get() = field
        private set(value) {
            field = value
        }

    constructor(permission:String){
        this.permission = permission
    }

}