package com.jair.utcstudents.presentation.carrer_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jair.utcstudents.model.Career

@Composable
fun CarrerListScreen() {
    CarrerList()
}

@Composable
fun CarrerList() {
    Column(
        modifier = Modifier
            .background(Color.LightGray) // Cambia el color de fondo
            .padding(16.dp) // Agrega padding
    ) {
        Career.values().forEach { carrera ->
            Text(
                text = carrera.career,
                style = TextStyle(
                    fontSize = 20.sp, // Cambia el tamaño del texto
                    fontWeight = FontWeight.Bold // Hace el texto en negrita
                ),
                color = Color.Black, // Cambia el color del texto
                modifier = Modifier.padding(vertical = 8.dp) // Agrega padding vertical
            )
        }
    }
}