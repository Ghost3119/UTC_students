package com.jair.utcstudents.presentation.student_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jair.utcstudents.repositories.StudentsRepositories
import com.jair.utcstudents.repositories.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class StudentListViewModel
@Inject constructor(
    private val studentsRepositories: StudentsRepositories
) : ViewModel() {
    private val _state: MutableState<StudentListState> = mutableStateOf(StudentListState())
    val state: State<StudentListState> = _state

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

    init {
        getStudentsList()
    }

    fun getStudentsList() {
        studentsRepositories.getStudentsList().onEach { result ->
            when(result) {
                is Result.Error -> {
                    _state.value = StudentListState(error = result.message ?: "Error occurred")
                }

                is Result.Loading -> {
                    _state.value = StudentListState(isLoading = true)
                }

                is Result.Success -> {
                    _state.value = StudentListState(students = result.data ?: emptyList())
                }

            }
        }.launchIn(viewModelScope)
    }
    fun deleteStudent(studentId: String) {
        studentsRepositories.deleteStudent(studentId)
    }

}
