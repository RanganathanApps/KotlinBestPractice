package com.example.kotlinbestpractice.movies.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ElevatedButton
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
fun SettingsScreen(navHostController: NavHostController){
    Box(modifier = Modifier.background(MaterialTheme.colorScheme.secondary)){

        Column(
            modifier = Modifier.fillMaxSize()
                .align(Alignment.Center),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Settings",
                style = TextStyle(
                    color = MaterialTheme.colorScheme.onPrimary
                )
            )

            Spacer(modifier = Modifier.width(119.dp))

            ElevatedButton(
                onClick = { navHostController.navigate(Screen.Profile.route) }
            ) {
                Text("Back")
            }



            Spacer(modifier = Modifier.height(19.dp))

            Text("Thing 1")
            Text("Thing 2")
            Text("Thing 3")
            Text("Thing 4")
            Text("Thing 5")
        }

    }
}
