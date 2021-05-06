package com.example.SecurityVersion1.Student.model

import javax.persistence.*

@Entity
@Table(name = "Student")
class Student {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    var id:Int

    var studentName:String

    constructor()
    {
        this.id = 0;
        this.studentName = ""
    }

    constructor(studentName:String){
        this.id = 0
        this.studentName = studentName
    }
}