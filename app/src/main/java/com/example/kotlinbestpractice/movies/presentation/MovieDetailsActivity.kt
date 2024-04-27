package com.example.kotlinbestpractice.movies.presentation

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.kotlinbestpractice.R
import com.example.kotlinbestpractice.movies.data.network.MovieApi
import com.example.kotlinbestpractice.movies.domain.model.Movie
import com.example.kotlinbestpractice.movies.navigation.Screen
import com.example.kotlinbestpractice.movies.presentation.ui.theme.KotlinBestPracticeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinBestPracticeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                }
            }
        }
    }
}


@Composable
fun MovieDetailScreen(
    navHostController: NavHostController,
    movieId: String,
    context: Context = LocalContext.current
) {


    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            //enableEdgeToEdge(statusBarStyle = SystemBarStyle.dark(Color.parseColor(“#801b1b1b”)))
            val window = (context as Activity).window
            window.statusBarColor = Color.Transparent.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false

        }
    }

    val viewModel = hiltViewModel<DetailViewModel>()

    val state by viewModel.uiState.collectAsStateWithLifecycle()

    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(lifecycleOwner) {
        viewModel.getMovieById(movieId)
    }

    when {
        state.isLoading == true -> {
            ShowProgressBar()
        }

        state.movie != null -> {
            LazyColumn {
                state.movie.let { movie ->
                    items(1) {
                        if (movie != null) {
                            MovieDetailCard(movie) {
                                navHostController.navigate(Screen.MovieTrailer.route +"/"+movieId)
                            }
                        }
                    }
                }

            }

        }

        state.error != null -> {

        }
    }

}

@Composable
fun MovieDetailCard(
    movie: Movie,
    context: Context = LocalContext.current,
    callBack: (movieId: Int) -> Unit
) {

    val painter =
        // Gray Scale Transformation
        rememberAsyncImagePainter(       // Circle Crop Transformation
            ImageRequest.Builder(LocalContext.current)
                .data(data = MovieApi.IMAGE_BASE_URL + movie.backdropPath).apply {
                }.build()
        )
    val state = painter.state

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            Image(
                painter = painter,
                contentDescription = "null",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .clip(RectangleShape)
                    .fillMaxWidth()
                    .height(350.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black),
                            startY = 500f
                        )
                    ),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .height(350.dp)
                        .padding(16.dp)
                        .align(Alignment.BottomStart),
                    contentAlignment = Alignment.BottomStart
                ) {
                    Column(
                    ) {
                        Text(
                            text = movie.title,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = MaterialTheme.typography.titleLarge.fontSize,
                            modifier = Modifier.padding(all = 4.dp)
                        )

                        Row {
                            movie.popularity?.let {
                                Text(
                                    text = it,
                                    color = Color.White,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                                    modifier = Modifier.padding(all = 4.dp)
                                )
                            }
                            movie.releaseDate?.let {
                                Text(
                                    text = it,
                                    color = Color.White,
                                    fontWeight = FontWeight.Light,
                                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                                    modifier = Modifier.padding(all = 4.dp)
                                )
                            }
                        }

                    }

                }
            }


        }

        Divider(color = Color.Black, thickness = 1.dp)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 10.dp)
        ) {
            movie.overview?.let {
                Text(
                    text = it,
                    color = Color.Gray,
                    fontWeight = FontWeight.Medium,
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                    modifier = Modifier.padding(all = 4.dp)
                )
            }

        }
        Divider(color = Color.Black, thickness = 1.dp)
        Spacer(modifier = Modifier.height(25.dp))
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 18.dp),
        ) {
            val sourceImage = painterResource(id = R.drawable.ic_launcher_foreground)
            Button(onClick = { callBack(movie.id) },
                modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    painter = sourceImage,
                    modifier = Modifier.size(30.dp),
                    contentDescription = "drawable icons",
                )
                Spacer(modifier = Modifier.width(15.dp))
                Text(
                    text = "Watch Trailer",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(.5f)
                )
            }
        }


    }

}
