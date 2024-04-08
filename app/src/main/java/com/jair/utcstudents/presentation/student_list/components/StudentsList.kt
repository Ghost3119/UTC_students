package com.jair.utcstudents.presentation.student_list.components

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.material.*
import androidx.compose.material.DismissDirection.*
import androidx.compose.material.DismissValue.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import com.jair.utcstudents.presentation.student_list.StudentListState

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun StudentsList(
    state: StudentListState,
    isRefreshing: Boolean,
    refreshData: () -> Unit,
    navController: NavController,
    onItemClick: (String) -> Unit,
    deleteStudent: (String) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing),
            onRefresh = { refreshData() }
        ) {
            LazyColumn(
                contentPadding = PaddingValues(16.dp)
            ) {
                items(
                    items = state.students,
                    key = { student ->
                        student.id
                    }
                ) { student ->
                    var isDeleted by remember {
                        mutableStateOf(false)
                    }
                    val dismissState = rememberDismissState(
                        confirmStateChange = {
                            Log.d("StudentList", "Dismiss value: ${it.name}")
                            if(it == DismissedToEnd) isDeleted = !isDeleted
                            it != DismissedToEnd
                        }
                    )
                    SwipeToDismiss(
                        state = dismissState,
                        directions = setOf(StartToEnd),
                        dismissThresholds = {
                            FractionalThreshold(0.5f)
                        },
                        background = {
                            val direction = dismissState.dismissDirection ?:  return@SwipeToDismiss
                            val color by animateColorAsState(
                                when(dismissState.targetValue) {
                                    Default -> Color.LightGray
                                    DismissedToEnd -> Color.Red
                                    DismissedToStart -> Color.Red
                                }
                            )
                            val alignment = when (direction) {
                                StartToEnd -> Alignment.CenterStart
                                EndToStart -> Alignment.CenterEnd
                            }
                            val icon = when (direction) {
                                StartToEnd -> Icons.Default.Delete
                                EndToStart -> Icons.Default.Delete
                            }
                            val scale by animateFloatAsState(
                                if (dismissState.targetValue == Default) 0.75f else 1f
                            )

                            Box(
                                Modifier
                                    .fillMaxSize()
                                    .background(color)
                                    .padding(horizontal = 20.dp),
                                contentAlignment = alignment
                            ) {
                                Icon(
                                    icon,
                                    contentDescription = "Localized description",
                                    modifier = Modifier.scale(scale)
                                )
                            }
                        }
                    ) {

                    if (isDeleted) {
                        deleteStudent(student.id)
                    } else {
                        StudentItem(
                            navController = navController,
                            student,
                            onItemClick = onItemClick
                        )
                    }
                }
            }
        }
        if (state.error.isNotBlank()) {
            Text(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(horizontal = 20.dp),
                text = state.error,
                textAlign = TextAlign.Center,
                color = Color.Red
            )
        }

        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}
}