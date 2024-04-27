package com.example.kotlinbestpractice.movies.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.kotlinbestpractice.movies.navigation.Screen
import com.example.kotlinbestpractice.ui.theme.KotlinBestPracticeTheme

@Composable
fun ProfileScreen(navHostController: NavHostController, movieId: String?){
    Box(modifier = Modifier.background(MaterialTheme.colorScheme.errorContainer)){

        Column(
            modifier = Modifier.fillMaxSize()
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Navigation",
                style = TextStyle(
                    color = MaterialTheme.colorScheme.onPrimary
                )
            )

            Spacer(modifier = Modifier.width(119.dp))

            Button(
                onClick = { navHostController.navigate(Screen.Notification.route) }
            ) {
                Text("MovieId is $movieId")
            }
        }

    }
}
