package com.example.kotlinbestpractice.movies.presentation.play

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.ActivityInfo
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.kotlinbestpractice.movies.presentation.DetailViewModel
import com.example.kotlinbestpractice.movies.presentation.MainViewModel
import com.example.kotlinbestpractice.movies.presentation.ShowProgressBar
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

/** Created By Ranga
on 18-04-2024
 **/
@Composable
fun YouTubeScreen(
    movieId: String,
    context: Context = LocalContext.current
) {
    LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
    val viewModel = hiltViewModel<DetailViewModel>()

    val state by viewModel.uiState.collectAsStateWithLifecycle()

    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(lifecycleOwner) {
        viewModel.getMovieTrailerVideos(movieId)
    }




    when {
        state.isLoading == true -> {
            ShowProgressBar()
        }

        state.trailers != null -> {
            if (state.trailers.isNullOrEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ){
                    Button(onClick = { }) {
                        Text("No Trailers for $movieId")
                    }
                }
                return
            }
            val view = LocalView.current
            if (!view.isInEditMode) {
                SideEffect {
                    //enableEdgeToEdge(statusBarStyle = SystemBarStyle.dark(Color.parseColor(“#801b1b1b”)))
                    val window = (context as Activity).window
                    window.statusBarColor = Color.Transparent.toArgb()
                    WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false

                }
            }
            var item = state.trailers!!.firstOrNull { it.type == "Trailer" }
            if (item == null) {
                item = state.trailers!!.firstOrNull { it.type == "Teaser" }
            }
            item?.let {

                Column(modifier = Modifier.fillMaxSize()) {
                    AndroidView(
                        factory = { context ->
                            val view = YouTubePlayerView(context)
                                .apply {
                                    lifecycleOwner.lifecycle.addObserver(this)
                                    val ypl = object : AbstractYouTubePlayerListener() {
                                        override fun onReady(youTubePlayer: YouTubePlayer) {
                                            super.onReady(youTubePlayer)
                                            youTubePlayer.loadVideo(it.key, 0f)
                                        }
                                    }
                                    addYouTubePlayerListener(ypl)
                                }
                            view
                        }
                    )
                }
            }

        }

        state.error != null -> {
            Button(onClick = { }) {
                Text("error ${state.error}")
            }
        }
    }


}

@Composable
fun LockScreenOrientation(orientation: Int) {
    val context = LocalContext.current
    DisposableEffect(orientation) {
        val activity = context.findActivity() ?: return@DisposableEffect onDispose {}
        val originalOrientation = activity.requestedOrientation
        activity.requestedOrientation = orientation
        onDispose {
            // restore original orientation when view disappears
            activity.requestedOrientation = originalOrientation
        }
    }
}

fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}