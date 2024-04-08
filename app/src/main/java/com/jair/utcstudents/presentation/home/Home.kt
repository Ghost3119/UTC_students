package com.jair.utcstudents.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jair.utcstudents.navigation.Destination
import com.jair.utcstudents.ui.theme.BrunswickGreen
import com.jair.utcstudents.ui.theme.Magnolia
import com.jair.utcstudents.ui.theme.Aquamarine
import com.jair.utcstudents.ui.theme.UTCStudentsTheme

@Composable
fun Home(NavController: NavController) {
    UTCStudentsTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    backgroundColor = Color(BrunswickGreen.value),
                    contentColor = Color(Magnolia.value),
                    title = {
                        Text(
                            text = "Universidad Tecnologica de Coahuila"
                        )
                    })
            },
        ) {  innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(innerPadding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {
                        NavController.navigate(Destination.StudentsList.route)
                    },
                    modifier = Modifier
                        .width(250.dp)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(Aquamarine.value),
                        contentColor = Color(Magnolia.value)
                    )
                ) {
                    Text(text = "Lista de alumnos inscritos")
                }
                Button(
                    onClick = { NavController.navigate(Destination.CarrersList.route) },
                    modifier = Modifier
                        .width(250.dp)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(Aquamarine.value),
                        contentColor = Color(Magnolia.value)
                    )
                ) {
                    Text(text = "Ver carreras")
                }
            }
        }
    }
}

