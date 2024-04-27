package com.example.kotlinbestpractice.movies.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.kotlinbestpractice.movies.data.network.MovieApi.Companion.IMAGE_BASE_URL
import com.example.kotlinbestpractice.movies.domain.model.Movie
import com.example.kotlinbestpractice.movies.navigation.Screen


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun HomeScreen(
    navHostController: NavHostController
) {

    MovieListContent(
        navHostController = navHostController
    )
}

@Composable
fun ShowProgressBar() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun MovieListContent(
    navHostController: NavHostController
) {
    val viewModel = hiltViewModel<MainViewModel>()

    val state by viewModel.uiState.collectAsStateWithLifecycle()

    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(lifecycleOwner) {
        if (state.data.isNullOrEmpty())
            viewModel.fetchMovies("2018")
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                bottom = bottomBarPaddingValues.calculateBottomPadding(),
                top = bottomBarPaddingValues.calculateBottomPadding()
            )
            .background(Color.White)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            when {
                state.isLoading == true -> {
                    ShowProgressBar()
                }

                !state.data.isNullOrEmpty() -> {

                    val years = listOf("2017","2018","2019", "2020", "2021", "2022", "2023", "2024")
                    Column {
                        LazyRow {
                            items(years.size) {
                                Box(Modifier.padding(8.dp)) {
                                    Button(onClick = {
                                        viewModel.fetchMovies(years[it])
                                    }) {
                                        Text(
                                            text = years[it],
                                            color = Color.White,
                                            fontWeight = FontWeight.Light,
                                            fontSize = MaterialTheme.typography.bodySmall.fontSize

                                        )
                                    }
                                }
                            }
                        }
                        LazyVerticalGrid(
                            state = viewModel.llState,
                            columns = GridCells.Fixed(2),
                            contentPadding = PaddingValues(
                                start = 2.dp,
                                top = 6.dp,
                                end = 2.dp,
                                bottom = 6.dp
                            ),
                        ) {
                            state.data?.let { movies ->
                                items(movies.size) { item ->
                                    MovieCard(viewModel, movies[item]) { movieId ->
                                        navHostController.navigate(Screen.MovieDetail.route + "/" + movieId.toString())
                                    }
                                }
                            }
                        }
                    }
                }

                state.error != null -> {
                    Button(onClick = { navHostController.navigate(Screen.Profile.route) }) {
                        Text("error ${state.error}")
                    }
                }
            }

        }
    }
}

@Preview
@Composable
fun MovieCard() {

}


@Composable
fun MovieCard(
    viewModel: MainViewModel,
    movie: Movie,
    context: Context = LocalContext.current,
    callBack: (movieId: Int) -> Unit
) {

    val scope = rememberCoroutineScope()
    val painter =
        // Gray Scale Transformation
        rememberAsyncImagePainter(       // Circle Crop Transformation
            ImageRequest.Builder(LocalContext.current)
                .data(data = IMAGE_BASE_URL + movie.backdropPath).apply {
                    CircleCropTransformation()
                }.build()
        )
    val state = painter.state

    val transition by animateFloatAsState(
        targetValue = if (state is AsyncImagePainter.State.Success) 1f else 0f, label = ""
    )
    Box(
        modifier = Modifier
            .padding(vertical = 12.dp, horizontal = 5.dp)
            .clip(shape = RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp))
            .height(220.dp)
            .clickable {
                callBack(movie.id)
            }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RectangleShape)
        ) {
            Image(
                painter = painter,
                contentDescription = "null",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(RectangleShape)
                    .fillMaxSize()
            )
            /* overlay */
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black),
                            startY = 250f
                        )
                    ),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .height(150.dp)
                        .align(Alignment.BottomStart),
                    contentAlignment = Alignment.BottomStart
                ) {
                    Column(
                    ) {
                        Text(
                            text = movie.title,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
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
    }


}

@Composable
fun SimpleToastMessage(message: String) {
    val context = LocalContext.current
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}





