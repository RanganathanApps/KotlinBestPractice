package com.example.kotlinbestpractice.movies.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.kotlinbestpractice.movies.presentation.HomeScreen
import com.example.kotlinbestpractice.movies.presentation.MovieDetailScreen
import com.example.kotlinbestpractice.movies.presentation.MyTopBar
import com.example.kotlinbestpractice.movies.presentation.NotificationScreen
import com.example.kotlinbestpractice.movies.presentation.ProfileScreen
import com.example.kotlinbestpractice.movies.presentation.SettingsScreen
import com.example.kotlinbestpractice.movies.presentation.SplashScreen
import com.example.kotlinbestpractice.movies.presentation.play.YouTubeScreen

/** Created By Ranga
on 15-04-2024
 **/
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomNavGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Movies.route
    ) {

        composable(
            route = Screen.Splash.route
        ) {
            SplashScreen(navController = navHostController)
        }

        composable(
            route = Screen.Movies.route
        ) {
            HomeScreen(navHostController)
        }

        composable(
            route = Screen.Profile.route
        ) {
        }

        composable(
            route = Screen.Settings.route
        ) {
            SettingsScreen(navHostController)
        }

        composable(
            route = Screen.Notification.route
        ) {
            NotificationScreen(navHostController)
        }

        navigation(
            startDestination = "login",
            route = "auth"
        ) {
            composable(route = "login",
                arguments = listOf(
                    navArgument("movieId") {
                        type = NavType.StringType
                        defaultValue = ""
                    }
                )) {
                //val viewModel = it.sharedViewModel<SampleViewModel>(navController)

                Button(onClick = {
                    navHostController.navigate("calendar") {
                        popUpTo("auth") {
                            inclusive = true
                        }
                    }
                }) {

                }
            }


            composable("register") {
                //val viewModel = it.sharedViewModel<SampleViewModel>(navController)
            }
            composable("forgot_password") {
                //val viewModel = it.sharedViewModel<SampleViewModel>(navController)
            }


        }

        navigation(
            startDestination = Screen.MovieDetail.route,
            route = "detail"
        ) {
            composable("calendar_overview") {

            }
            composable("calendar_entry") {

            }

            composable(
                route = Screen.MovieDetail.route + "/{movieId}",
                arguments = listOf(
                    navArgument("movieId") {
                        type = NavType.StringType
                        defaultValue = ""
                    }
                )
            ) {
                val movieId = it.arguments?.getString("movieId")
                Scaffold(modifier = Modifier
                    .fillMaxSize(),
                    /*topBar = {
                        MyTopBar(scrollBehavior, navHostController)
                    }*/
                ) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        if (movieId != null) {
                            MovieDetailScreen(navHostController, movieId)
                        }

                    }
                }
            }
            composable(
                route = Screen.MovieTrailer.route + "/{movieId}",
                arguments = listOf(
                    navArgument("movieId") {
                        type = NavType.StringType
                        defaultValue = ""
                    }
                )
            ){
                val movieId = it.arguments?.getString("movieId")
                Column(modifier = Modifier.fillMaxSize()) {
                    if (movieId != null) {
                        YouTubeScreen(movieId)
                    }

                }
            }
        }


    }
}