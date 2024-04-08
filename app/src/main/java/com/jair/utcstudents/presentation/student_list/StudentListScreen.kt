package com.jair.utcstudents.presentation.student_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.jair.utcstudents.navigation.Destination
import com.jair.utcstudents.presentation.student_list.components.StudentItem
import com.jair.utcstudents.presentation.student_list.components.StudentsList
import com.jair.utcstudents.ui.theme.BrunswickGreen
import com.jair.utcstudents.ui.theme.Magnolia
import com.jair.utcstudents.ui.theme.Aquamarine

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentListScreen(
    state: StudentListState,
    navController: NavController,
    isRefreshing: Boolean,
    refreshData: () -> Unit,
    onItemClick: (String) -> Unit,
    deleteStudent: (String) -> Unit
){

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = Color(Aquamarine.value),
                    titleContentColor = Color(Magnolia.value),
                ),
                title = {
                    Text("Lista de alumnos inscritos")
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(Destination.StudentInscription.route)
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) {

        innerPadding ->
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
            onRefresh = refreshData) {
            Column(
                modifier = Modifier
                    .padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                StudentsList(
                    state = state,
                    isRefreshing = isRefreshing,
                    refreshData = { refreshData() },
                    navController = navController,
                    onItemClick = onItemClick,
                    deleteStudent = deleteStudent
                )
            }
        }

    }
}