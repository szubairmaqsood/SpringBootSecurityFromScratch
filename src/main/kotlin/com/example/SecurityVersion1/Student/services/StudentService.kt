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

    fun listAllStudents():ResponseEntity<Collection<Student>>{
        return  ResponseEntity(studentRepository.findAll(),HttpStatus.OK)
    }

    fun showStudent(id:Int): ResponseEntity<Optional<Student>> {
        var student:Optional<Student> = studentRepository.findById(id)
        if(student.isPresent){
            return  ResponseEntity(student, HttpStatus.OK)
        }else{
            return  ResponseEntity.notFound().build();
        }
    }

    fun addStudent(student:Student):ResponseEntity<Student>{
        studentRepository.saveAndFlush(student)
        return  ResponseEntity(student,HttpStatus.CREATED)
    }

    fun DeleteStudent(id:Int): ResponseEntity<String> {
        var student:Optional<Student> = studentRepository.findById(id)
        if(student.isPresent){
            studentRepository.deleteById(id)
            return ResponseEntity("The Student with id " + id.toString()  + " deleted successfully",HttpStatus.OK)
        }
        else{
            return ResponseEntity("The Student with id " + id.toString()  + " does not exist",HttpStatus.NOT_FOUND)
        }
    }

    fun updateStudent(id:Int,student:Student):ResponseEntity<Student>{
        var _student:Optional<Student> = studentRepository.findById(id)

        if(_student.isPresent) {
            var student1:Student    =_student.get()
            student1.studentName = student.studentName
            studentRepository.saveAndFlush(student1)
            return ResponseEntity(student1,HttpStatus.OK)
        }
        else{
            return ResponseEntity.notFound().build()
        }
    }
}