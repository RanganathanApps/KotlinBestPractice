package com.example.kotlinbestpractice.movies.data.remote

import com.example.kotlinbestpractice.movies.domain.model.Movie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("")
    suspend fun getMovies(): MovieApiResponse

    @GET("movie/popular?language=en-US&page=1")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = API_KEY
    ): MovieApiResponse

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
        const val API_KEY = "d1c147aa41770b316e51950947045b70"
        const val token =
            "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkMWMxNDdhYTQxNzcwYjMxNmU1MTk1MDk0NzA0NWI3MCIsInN1YiI6IjVlMzlhOThlYWM4ZTZiMDAxNWM4NzE3ZiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.UPDtQsLw1HbiN3XrPGeI0nbAKyQKQCmYVPChUx_KaLI"
    }
}