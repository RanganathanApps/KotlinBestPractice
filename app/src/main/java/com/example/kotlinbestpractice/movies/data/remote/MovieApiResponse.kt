package com.example.kotlinbestpractice.movies.data.remote

import com.example.kotlinbestpractice.movies.domain.model.Movie

/** Created By Ranga
on 04-04-2024
 **/
class MovieApiResponse(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)
