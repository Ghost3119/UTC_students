package com.jair.utcstudents.presentation.student_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jair.utcstudents.model.Student
import com.jair.utcstudents.ui.theme.UTCStudentsTheme

@Composable
fun StudentItem(navController: NavController,student: Student,
                onItemClick:(String) -> Unit){
    UTCStudentsTheme {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colors.surface,

            ),
            modifier = Modifier
                .clickable {
                    onItemClick(student.id)
                }
        ) {
            Row (modifier = Modifier
                .fillMaxWidth() ){
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ){
                    Text(
                        text = "Alumno: ${student.name}",
                        modifier = Modifier
                            .padding(16.dp),
                        textAlign = TextAlign.Center,
                    )
                    Text(text = "Email: ${student.email}",
                        modifier = Modifier
                            .padding(16.dp),
                        textAlign = TextAlign.Center,
                    )
                    Text(
                        text = "Matricula: ${student.registration}",
                        modifier = Modifier
                            .padding(16.dp),
                        textAlign = TextAlign.Center,
                    )
                    Text(
                        text = "Carrera: ${student.career}",
                        modifier = Modifier
                            .padding(16.dp),
                        textAlign = TextAlign.Center,
                    )
                }
            }

        }
    }
}