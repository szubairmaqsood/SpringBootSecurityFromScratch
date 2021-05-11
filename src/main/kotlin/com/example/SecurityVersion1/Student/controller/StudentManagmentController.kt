package com.example.SecurityVersion1.Student.controller

import com.example.SecurityVersion1.Student.model.Student
import com.example.SecurityVersion1.Student.services.StudentService
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@Api(value="Api for Admin of Different Levels", description="This api is just made for Admin access")
@RequestMapping("/managment/api/v1/students")
class StudentManagmentController {

    var studentService: StudentService
    @Autowired
    constructor(_studentService: StudentService){
        this.studentService = _studentService
    }

    /*
      Read Operations
     */
    @GetMapping("/list")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADMINTRAINEE')")
    fun list(): ResponseEntity<Collection<Student>> {
        return this.studentService.listAllStudents()
    }


    @GetMapping("/show/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADMINTRAINEE')")
    fun showStudent(@PathVariable id:Int):ResponseEntity<Optional<Student>>{
        return this.studentService.showStudent(id)
    }

    /*
       Write Operations
     */

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('student:write')")
    fun registerStudent(@RequestBody student:Student):ResponseEntity<Student>{
        return this.studentService.addStudent(student)
    }



    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('student:write')")
    fun updateStudent(@PathVariable id: Int, @RequestBody student:Student):ResponseEntity<Student>{
    return this.studentService.updateStudent(id,student)
    }


    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('student:write')")
    fun deleteStudent(@PathVariable id: Int): ResponseEntity<String> {
        return this.studentService.DeleteStudent(id)
    }

}