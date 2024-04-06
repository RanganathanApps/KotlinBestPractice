package com.example.kotlinbestpractice.movies.domain.repository

import com.example.kotlinbestpractice.movies.domain.model.Movie
import com.example.kotlinbestpractice.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    fun getPopularMovies(): Flow<NetworkResult<List<Movie>>>

    suspend fun getMovies(
        forceFetchFromRemote: Boolean,
        category: String,
        page: Int
    ): Flow<NetworkResult<List<Movie>>>

    suspend fun getMovie(id: Int): Flow<NetworkResult<Movie>>
}