package com.aad.composefinalproject.ui.component.navigation


/****************************************************
 * Created by Indra Muliana
 * On Sunday, 27/11/2022 19.03
 * Email: indra.ndra26@gmail.com
 * Github: https://github.com/indra-yana
 ****************************************************/

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Profile : Screen("profile")
    object DetailMovie : Screen("home/{movieId}") {
        fun createRoute(movieId: Int) = "home/$movieId"
    }
}
