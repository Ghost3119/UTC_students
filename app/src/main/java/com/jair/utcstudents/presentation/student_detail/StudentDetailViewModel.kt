package com.jair.utcstudents.presentation.student_detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jair.utcstudents.model.Student
import com.jair.utcstudents.presentation.student_list.StudentListState
import com.jair.utcstudents.repositories.Result
import com.jair.utcstudents.repositories.StudentsRepositories
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class StudentDetailViewModel
@Inject constructor(
    private val studentsRepositories: StudentsRepositories,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state: MutableState<StudentDetailState> = mutableStateOf(StudentDetailState())


    val state: State<StudentDetailState>
        get() = _state

    init {
        savedStateHandle.get<String>("studentId")?.let { studentId ->
            getStudentById(studentId)
        }
    }

    fun addNewStudent(name: String, email: String, registration: String, career: String) {
        val student = Student(
            id = UUID.randomUUID().toString(),
            name = name,
            email = email,
            registration = registration,
            career = career
        )
        studentsRepositories.addNewStudent(student)
    }

    private fun getStudentById(studentId: String) {
        studentsRepositories.getStudentById(studentId).onEach { result ->
            when(result) {
                is Result.Error -> {
                    _state.value = StudentDetailState(error = result.message ?: "Error occurred")
                }

                is Result.Loading -> {
                    _state.value = StudentDetailState(isLoading = true)
                }

                is Result.Success -> {
                    _state.value = StudentDetailState(student = result.data)
                }

            }
        }.launchIn(viewModelScope)
    }

    fun updateStudent(newName: String, newEmail: String, newRegistration:String, newCareer:String){
        if (state.value.student == null){
            _state.value = StudentDetailState(error = "Error occurred")
            return
        }

        val studentEdited = state.value.student!!.copy(
            name = newName,
            email = newEmail,
            registration = newRegistration,
            career = newCareer
        )

        studentsRepositories.updateStudent(studentEdited.id,studentEdited)

    }
}