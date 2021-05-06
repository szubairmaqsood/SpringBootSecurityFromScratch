package com.example.SecurityVersion1.Student.services

import com.example.SecurityVersion1.Student.model.Student
import com.example.SecurityVersion1.Student.repository.StudentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
class StudentService {
    var  studentRepository: StudentRepository

    @Autowired
    constructor(_studentRepository: StudentRepository ){
        this.studentRepository = _studentRepository
    }

    fun showStudent(id:Int): ResponseEntity<Optional<Student>> {
        var student:Optional<Student> = studentRepository.findById(id)
        if(student.isPresent){
            return  ResponseEntity(student, HttpStatus.OK)
        }else{
            return  ResponseEntity.notFound().build();
        }

    }
}