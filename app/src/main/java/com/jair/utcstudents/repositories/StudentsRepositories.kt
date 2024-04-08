package com.jair.utcstudents.repositories

import com.google.firebase.firestore.CollectionReference
import com.jair.utcstudents.model.Student
import com.jair.utcstudents.repositories.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class StudentsRepositories
@Inject constructor(
    private val studentsList: CollectionReference
)
{


    fun addNewStudent(student : Student){
        try {
            studentsList.document(student.id).set(student)
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    fun getStudentsList() : Flow<Result<List<Student>>> = flow {
        try {
            emit(Result.Loading<List<Student>>())

            val studentsList = studentsList.get().await().map{
                document ->  document.toObject(Student::class.java)
            }

            emit(Result.Success<List<Student>>(data = studentsList))
        }catch (e: Exception){
            emit(Result.Error<List<Student>>(message = e.localizedMessage ?: "An unexpected error occurred" ))
        }
    }

    fun getStudentById( studentId: String): Flow<Result<Student>> = flow{
        try {
            emit(Result.Loading<Student>())

            val student = studentsList
                .whereGreaterThanOrEqualTo("id", studentId)
                .get()
                .await()
                .toObjects(Student::class.java)
                .first()

            emit(Result.Success<Student>(data = student))
        }catch (e: Exception){
            emit(Result.Error<Student>(message = e.localizedMessage ?: "An unexpected error occurred" ))
        }
    }

    fun updateStudent(studentId: String, student: Student){
        try {
            val map = mapOf(
                "name" to student.name,
                "email" to student.email,
                "registration" to student.registration,
                "career" to student.career
             )

            studentsList.document(studentId).update(map)
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    fun deleteStudent(studentId: String){
        try {
         studentsList.document(studentId).delete()
        }catch (e: Exception){
            e.printStackTrace()
        }
    }
}