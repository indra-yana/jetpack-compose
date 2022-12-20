package com.aad.composefinalproject

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aad.composefinalproject.repository.MovieRepository
import com.aad.composefinalproject.ui.component.BottomBar
import com.aad.composefinalproject.ui.component.navigation.Screen
import com.aad.composefinalproject.ui.pages.DetailMovieScreen
import com.aad.composefinalproject.ui.pages.HomeScreen
import com.aad.composefinalproject.ui.pages.ProfileScreen
import com.aad.composefinalproject.ui.theme.ComposeFinalProjectTheme

/****************************************************
 * Created by Indra Muliana
 * On Monday, 12/12/2022 19.57
 * Email: indra.ndra26@gmail.com
 * Github: https://github.com/indra-yana
 ****************************************************/

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun JetMovieApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val context = LocalContext.current
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.DetailMovie.route) {
                BottomBar(navController)
            }
        },
        modifier = modifier,
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    viewModel = viewModel(factory = ViewModelFactory(MovieRepository(), context)),
                    navigateToDetail = { movieId ->
                        navController.navigate(Screen.DetailMovie.createRoute(movieId))
                    }
                )
            }

            composable(Screen.Profile.route) {
                ProfileScreen(
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }

            composable(
                route = Screen.DetailMovie.route,
                arguments = listOf(
                    navArgument("movieId") {
                        type = NavType.IntType
                    }
                )
            ) {
                val id = it.arguments?.getInt("movieId") ?: 0
                DetailMovieScreen(
                    movieId = id,
                    viewModel = viewModel(factory = ViewModelFactory(MovieRepository(), context)),
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun JetMovieAppPreview() {
    ComposeFinalProjectTheme {
        JetMovieApp()
    }
}