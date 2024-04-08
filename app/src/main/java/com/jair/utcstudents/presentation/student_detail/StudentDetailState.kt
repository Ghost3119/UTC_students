package com.jair.utcstudents.presentation.student_detail

import com.jair.utcstudents.model.Student

data class StudentDetailState(
    val isLoading : Boolean = false,
    val student : Student? = null,
    val error : String = ""
)
