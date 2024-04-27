package com.example.kotlinbestpractice.movies.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    val id: Int,
    val title: String,
    @SerialName("poster_path")
    val posterPath: String? = null,
    @SerialName("genre_ids")
    val genreIds: List<Int>? = null,
    @SerialName("original_title")
    val originalTitle: String,
    val overview: String? = null,
    @SerialName("release_date")
    val releaseDate: String? = null,
    @SerialName("vote_average")
    val voteAverage: Float? = null,
    @SerialName("backdrop_path")
    val backdropPath: String? = null,
    val popularity: String? = null,
    @SerialName("category")
    val category: String? = null
    )
