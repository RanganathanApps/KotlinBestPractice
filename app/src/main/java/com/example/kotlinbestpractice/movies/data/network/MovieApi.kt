package com.example.kotlinbestpractice.movies.data.network

import com.example.kotlinbestpractice.movies.domain.model.Movie
import com.example.kotlinbestpractice.movies.domain.model.MovieApiResponse
import com.example.kotlinbestpractice.movies.domain.model.MovieApiVideosResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("")
    suspend fun getMovies(): MovieApiResponse

    //@GET("movie/popular?language=en-US&page=1")
    @GET("discover/movie?page=1")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("with_original_language") withOriginalLanguage: String = "ta",
        @Query("year") year: String = "2022"
    ): MovieApiResponse

    @GET("movie/{id}")
    suspend fun getMovie(
        @Path("id") id: String = "420055",
        @Query("api_key") apiKey: String = API_KEY
    ): Movie


    @GET("movie/{id}/videos")
    suspend fun getMovieTrailerVideos(
        @Path("id") id: String = "420055",
        @Query("api_key") apiKey: String = API_KEY
    ): MovieApiVideosResponse

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
        const val API_KEY = "d1c147aa41770b316e51950947045b70"
        const val token =
            "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkMWMxNDdhYTQxNzcwYjMxNmU1MTk1MDk0NzA0NWI3MCIsInN1YiI6IjVlMzlhOThlYWM4ZTZiMDAxNWM4NzE3ZiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.UPDtQsLw1HbiN3XrPGeI0nbAKyQKQCmYVPChUx_KaLI"
    }
}