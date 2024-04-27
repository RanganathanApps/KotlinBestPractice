package com.example.kotlinbestpractice.movies.navigation

import androidx.compose.ui.graphics.vector.ImageVector

/** Created By Ranga
on 15-04-2024
 **/
data class BottomNavItem(
    val route: String,
    val title: String,
    val iconSelected: ImageVector,
    val iconUnSelected: ImageVector,
    val hasNews: Boolean =  false,
    val badge: Int? = null
)
