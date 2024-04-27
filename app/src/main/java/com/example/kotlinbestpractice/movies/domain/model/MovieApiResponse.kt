package com.example.kotlinbestpractice.movies.domain.model

import com.example.kotlinbestpractice.movies.domain.model.Movie
import com.example.kotlinbestpractice.movies.domain.model.Trailer
import kotlinx.serialization.Serializable

/** Created By Ranga
on 04-04-2024
 **/
@Serializable
class MovieApiResponse(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)

@Serializable
class MovieApiVideosResponse(
    val id: String,
    val results: List<Trailer>
)
