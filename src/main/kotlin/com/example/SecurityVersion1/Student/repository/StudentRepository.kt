package com.example.SecurityVersion1.Student.repository

import com.example.SecurityVersion1.Student.model.Student
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StudentRepository:JpaRepository<Student,Int> {
}