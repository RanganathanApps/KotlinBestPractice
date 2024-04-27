package com.example.kotlinbestpractice.movies.navigation

/** Created By Ranga
on 15-04-2024
 **/
sealed class Screen(
    val route : String
    ) {

    data object Splash : Screen(
        route = "Splash"
    )

    data object Movies : Screen(
        route = "Movies"
    )

    data object Profile : Screen(
        route = "Profile"
    )

    data object Settings : Screen(
        route = "More"
    )

    data object Notification : Screen(
        route = "Notifications"
    )
    data object MovieDetail : Screen(
        route = "MovieDetail"
    )
    data object MovieTrailer : Screen(
        route = "MovieTrailer"
    )
}