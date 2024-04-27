package com.example.kotlinbestpractice.movies.domain.repository

import com.example.kotlinbestpractice.movies.domain.model.Movie
import com.example.kotlinbestpractice.movies.domain.model.MovieApiResponse
import com.example.kotlinbestpractice.movies.domain.model.MovieApiVideosResponse
import com.example.kotlinbestpractice.movies.domain.model.Trailer
import com.example.kotlinbestpractice.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    suspend fun getMovies(
        year: String
    ): Flow<NetworkResult<MovieApiResponse>>

    suspend fun getMovie(id: String):
            Flow<NetworkResult<Movie>>
    suspend fun getMovieVideos(id: String):
            Flow<NetworkResult<MovieApiVideosResponse>>
}