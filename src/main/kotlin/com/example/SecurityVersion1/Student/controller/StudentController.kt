package com.example.SecurityVersion1.Student.controller

import com.example.SecurityVersion1.Student.model.Student
import com.example.SecurityVersion1.Student.services.StudentService
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@Api(value="Api for Student", description="This api is just made for students access")
@RequestMapping("/api/v1/students")
class StudentController {

    var studentService: StudentService
    @Autowired
    constructor(_studentService: StudentService){
        this.studentService = _studentService
    }

    @GetMapping("/{studentId}")
    fun getStudent(@PathVariable studentId:Int):ResponseEntity<Optional<Student>>{
        return studentService.showStudent(studentId)
    }
}