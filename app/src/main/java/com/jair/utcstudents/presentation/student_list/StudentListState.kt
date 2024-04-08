package com.jair.utcstudents.presentation.student_list

import com.jair.utcstudents.model.Student

data class StudentListState(
    val isLoading: Boolean = false,
    val students: List<Student> = emptyList(),
    val error: String = ""
)