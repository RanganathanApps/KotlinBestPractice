package com.example.kotlinbestpractice.movies.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.kotlinbestpractice.movies.navigation.BottomNavGraph
import com.example.kotlinbestpractice.movies.navigation.BottomNavItem
import com.example.kotlinbestpractice.movies.navigation.Screen
import com.example.kotlinbestpractice.ui.theme.Purple40

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navHostController = rememberNavController()
    var showBottomBar by rememberSaveable { mutableStateOf(true) }
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()

    showBottomBar = when (navBackStackEntry?.destination?.route) {
        Screen.MovieDetail.route -> false // on this screen bottom bar should be hidden
        Screen.MovieTrailer.route -> false // on this screen bottom bar should be hidden
        else -> true // in all other cases show bottom bar
    }


    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = {
            if (showBottomBar)
                MyBottomBar(navController = navHostController)
        }
    ) {
        Column(modifier = Modifier.padding(bottom = it.calculateBottomPadding(),
            top = it.calculateBottomPadding())) {
            BottomNavGraph(navHostController = navHostController)
        }
    }
}

@Composable
public fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.arguments?.getString("route")
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(scrollBehavior: TopAppBarScrollBehavior, navHostController: NavHostController) {
    TopAppBar(
        title = {
            Text(text = "Movies - IMDB")
        },
        navigationIcon = {
            IconButton(onClick = {
                navHostController.popBackStack()
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Go Back"
                )
            }
        },
        actions = {
            IconButton(onClick = { navHostController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Go Back"
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBottomBar(navController: NavHostController) {

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination

    var selectedValueIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    NavigationBar {
        contentColorFor(backgroundColor = Color.Red)
        navigationBarItems.forEachIndexed { index, navigationBarItem ->
            NavigationBarItem(
                alwaysShowLabel = true,
                label = {
                    Text(text = navigationBarItem.title)
                },
                selected = selectedValueIndex == index,
                onClick = {
                    selectedValueIndex = index
                    navController.navigate(navigationBarItem.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                },
                icon = {
                    BadgedBox(
                        badge = {
                            if (navigationBarItem.badge != null) {
                                Badge {
                                    Text(text = navigationBarItem.badge.toString())
                                }
                            } else if (navigationBarItem.hasNews) {
                                Badge()
                            }
                        },
                    ) {
                        Icon(
                            imageVector = if (selectedValueIndex == index) navigationBarItem.iconSelected
                            else navigationBarItem.iconUnSelected,
                            contentDescription = navigationBarItem.title,
                            tint = Purple40
                        )
                    }
                }
            )
        }
    }
}


@Composable
fun RowScope.AddItem(
    navController: NavHostController,
    currentDestination: NavDestination,
    movieScreen: BottomNavItem
) {
    NavigationBarItem(
        label = {
            Text(text = movieScreen.title)
        },
        selected = currentDestination.hierarchy.any {
            it.route == movieScreen.route
        },
        onClick = {
            navController.navigate(movieScreen.route) {
                popUpTo(navController.graph.startDestinationId)
                launchSingleTop = true
            }
        },
        icon = {
            Icon(imageVector = movieScreen.iconSelected, contentDescription = movieScreen.title)
        }
    )
}


var bottomBarPaddingValues: PaddingValues = PaddingValues()
var topAppBarPaddingValues: PaddingValues = PaddingValues()
val navigationBarItems = listOf(
    BottomNavItem(
        route = "Movies",
        title = "Movies",
        iconSelected = Icons.Filled.PlayArrow,
        iconUnSelected = Icons.Default.PlayArrow
    ),
    BottomNavItem(
        route = "Notifications",
        title = "Notifications",
        iconSelected = Icons.Filled.Notifications,
        iconUnSelected = Icons.Default.Notifications,
        hasNews = false,
        badge = 1
    ),
    BottomNavItem(
        route = "Profile",
        title = "Profile",
        iconSelected = Icons.Filled.Person,
        iconUnSelected = Icons.Default.Person
    ),
    BottomNavItem(
        route = "More",
        title = "More",
        iconSelected = Icons.Filled.Menu,
        iconUnSelected = Icons.Default.Menu,
        hasNews = true
    )

)



