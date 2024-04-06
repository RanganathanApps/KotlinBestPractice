package com.example.kotlinbestpractice.utils

/** Created By Ranga
on 04-04-2024
 **/
sealed class NetworkResult<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T): NetworkResult<T>(data)

    class Error<T>(message: String, data: T?):
        NetworkResult<T>(data, message)

    class Loading<T>(val isLoading: Boolean = true): NetworkResult<T>(null)
}