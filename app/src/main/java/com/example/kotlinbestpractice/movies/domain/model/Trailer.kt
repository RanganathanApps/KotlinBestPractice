package com.example.kotlinbestpractice.movies.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Trailer(
    val type: String,
    val name: String,
    val key: String,
    val official: Boolean,
    val id: String
)
