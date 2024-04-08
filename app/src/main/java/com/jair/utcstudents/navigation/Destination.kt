package com.jair.utcstudents.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.navArgument

sealed class Destination(
    val route:String,
    val arguments: List<NamedNavArgument>) {

    object Home: Destination(
        route = "home",
        arguments = emptyList()
    )
    object StudentsList: Destination(
        route = "studentsList",
        arguments = emptyList()
    )
    object StudentDetail: Destination(
        route = "studentDetail",
        arguments = listOf(
            navArgument("studentId"){
                nullable = true
            }

        )
    )
    object StudentInscription: Destination(
        route = "studentInscription",
        arguments = listOf(
            navArgument("studentId"){
                nullable = true
            }

        )
    )
    object CarrersList: Destination(
        route = "carrersList",
        arguments = emptyList()
    )

}

