package com.jair.utcstudents.model

data class Student(
    val id: String,
    val name: String,
    val email: String,
    val registration: String,
    val career: String,
){
    constructor(): this("","","","","")
}
