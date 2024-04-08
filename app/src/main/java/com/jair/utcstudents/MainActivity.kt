package com.jair.utcstudents

import android.os.Bundle
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jair.utcstudents.navigation.Destination
import com.jair.utcstudents.presentation.carrer_list.CarrerListScreen
import com.jair.utcstudents.presentation.home.Home
import com.jair.utcstudents.presentation.student_detail.StudentDetailScreen
import com.jair.utcstudents.presentation.student_detail.StudentDetailViewModel
import com.jair.utcstudents.presentation.student_inscription.StudentInscription
import com.jair.utcstudents.presentation.student_list.StudentListScreen
import com.jair.utcstudents.presentation.student_list.StudentListViewModel
import com.jair.utcstudents.ui.theme.UTCStudentsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UTCStudentsTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = Destination.Home.route) {
                    home(navController)
                    studentList(navController)
                    studentDetail()
                    studentInscription()
                    carrersList()
                }
            }
        }
    }
}

fun NavGraphBuilder.home(navController: NavController) {
    composable(Destination.Home.route) {
        Home(navController)
    }
}

fun NavGraphBuilder.studentList(navController: NavController) {
    composable(Destination.StudentsList.route) {

        val viewModel: StudentListViewModel = hiltViewModel()
        val state = viewModel.state.value
        val isRefreshing = viewModel.isRefreshing.collectAsState()

        StudentListScreen(
            state = state,
            navController = navController,
            isRefreshing = isRefreshing.value,
            refreshData = viewModel::getStudentsList,
            onItemClick = { studentId ->
                navController.navigate(Destination.StudentInscription.route + "?studentId=$studentId")
            },
            deleteStudent = viewModel::deleteStudent
        )
    }
}
    fun NavGraphBuilder.studentDetail() {
        composable(
            route = Destination.StudentDetail.route + "?studentId={studentId}",) {

            StudentDetailScreen(
            )
        }
    }

    fun NavGraphBuilder.studentInscription() {
        composable(
            route = Destination.StudentInscription.route + "?studentId={studentId}",) {
            val viewModel: StudentDetailViewModel = hiltViewModel()
            val state = viewModel.state.value

            StudentInscription(
                state = state,
                addNewStudent = viewModel::addNewStudent,
                updateStudent = viewModel::updateStudent
            )
        }
    }

fun NavGraphBuilder.carrersList() {
    composable(Destination.CarrersList.route) {
        CarrerListScreen()
    }
}