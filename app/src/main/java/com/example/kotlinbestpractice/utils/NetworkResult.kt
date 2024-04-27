package com.example.kotlinbestpractice.utils

import androidx.compose.runtime.Composable

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


    /**
     * Returns data from a [Success].
     * @throws ClassCastException If the current state is not [Success]
     *  */
    fun getSuccessData() = (this as Success).data
    fun getSuccessDataOrNull(): T? {
        return try {
            (this as Success).data
        } catch (e: Exception) {
            null
        }
    }

    /**
     * Returns an error message from an [Error]
     * @throws ClassCastException If the current state is not [Error]
     *  */
    fun getErrorMessage() = (this as Error).message
    fun getErrorMessageOrNull(): String? {
        return try {
            (this as Error).message
        } catch (e: Exception) {
            null
        }
    }

    @Composable
    fun DisplayResult(
        onLoading: @Composable () -> Unit,
        onSuccess: @Composable () -> Unit,
        onError: @Composable () -> Unit
    ){
        when(this){
            is Loading -> onLoading()
            is Error -> onSuccess()
            is Success -> onError()
        }
    }
}