package com.jair.utcstudents.presentation.student_detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jair.utcstudents.model.Career
import com.jair.utcstudents.ui.theme.Aquamarine
import com.jair.utcstudents.ui.theme.BrunswickGreen
import com.jair.utcstudents.ui.theme.Magnolia
import com.jair.utcstudents.ui.theme.Rojo
import com.jair.utcstudents.ui.theme.UTCStudentsTheme

@Preview
@Composable
fun StudentDetailScreen() {
    var studentName by remember { mutableStateOf("") }
    var studentEmail by remember { mutableStateOf("") }
    var studentRegistration by remember { mutableStateOf("") }
    var studentCareer by remember { mutableStateOf("") }


    UTCStudentsTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    backgroundColor = Color(BrunswickGreen.value),
                    contentColor = Color(Magnolia.value),
                    title = {
                        Text(
                            text = "Editar alumno"
                        )
                    })
            },

            ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)

            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        value = studentName,
                        onValueChange = { studentName = it },
                        label = {
                            Text(text = "Name")
                        }
                    )

                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        value = studentEmail,
                        onValueChange = { studentEmail = it },
                        label = {
                            Text(text = "Email")
                        }
                    )

                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        value = studentRegistration,
                        onValueChange = { studentRegistration = it },
                        label = {
                            Text(text = "Matricula")
                        }
                    )

                    SimpleDropdown(
                        items = getAllCareerNames(),
                        onItemSelected = { studentCareer = it }
                    )
/*
                    if (TODO()) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp), text = state.error,
                            style = TextStyle(
                                color = Color(Rojo.value),
                                textAlign = TextAlign.Center
                            )
                        )
                    }
*/
                    /*
                    if (TODO()) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                        }
                    } else {
                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp),
                            onClick = {

                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(Aquamarine.value))
                        ) {
                            Text(text = "Save")
                        }
                    }
*/

                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SimpleDropdown(items: List<String>, onItemSelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf(items.firstOrNull() ?: "") }

    Surface(
        shape = MaterialTheme.shapes.medium,
        contentColor = MaterialTheme.colors.onSurface,
        onClick = { expanded = !expanded }
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(text = if (expanded) "Select..." else selectedItem)
            Icon(
                Icons.Filled.ArrowDropDown,
                contentDescription = "Expand dropdown",
                modifier = Modifier.clickable { expanded = !expanded }
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    onClick = {
                        onItemSelected(item)
                        selectedItem = item
                        expanded = false
                    }
                ) {
                    Text(text = item)
                }
            }
        }
    }
}

fun getAllCareerNames(): List<String> {
    return Career.values().map { it.career }
}