package com.example.kotlinbestpractice.movies.data.network

/** Created By Ranga
on 20-04-2024
 **/
object HttpRoutes {

    private const val BASE_URL = "https://api.themoviedb.org/3/"
    const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
    const val API_KEY = "d1c147aa41770b316e51950947045b70"
    const val token =
        "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkMWMxNDdhYTQxNzcwYjMxNmU1MTk1MDk0NzA0NWI3MCIsInN1YiI6IjVlMzlhOThlYWM4ZTZiMDAxNWM4NzE3ZiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.UPDtQsLw1HbiN3XrPGeI0nbAKyQKQCmYVPChUx_KaLI"

    const val MOVIES = BASE_URL + "discover/movie?page=1"
    const val MOVIE = BASE_URL + "movie/"
    const val MOVIE_TRAILERS = BASE_URL + "movie/id/videos"
}