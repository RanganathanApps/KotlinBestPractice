package com.example.kotlinbestpractice.movies.presentation

import com.example.kotlinbestpractice.movies.domain.model.Movie
import com.example.kotlinbestpractice.movies.domain.model.Trailer
import java.lang.Error

/** Created By Ranga
on 15-04-2024
 **/
data class MovieScreenState(
    val isLoading: Boolean? = false,
    val isLoadingTrailers: Boolean? = false,
    val data: List<Movie>? = null,
    val trailers: List<Trailer>? = null,
    val movie: Movie? = null,
    val error: String? =null,
    val errorLoadingTrailers: String? =null
)