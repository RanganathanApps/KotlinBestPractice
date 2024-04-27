package com.example.kotlinbestpractice.movies.data.repository

import com.example.kotlinbestpractice.movies.data.network.MovieApi
import com.example.kotlinbestpractice.movies.data.network.MoviesApiService
import com.example.kotlinbestpractice.movies.domain.model.Movie
import com.example.kotlinbestpractice.movies.domain.model.MovieApiResponse
import com.example.kotlinbestpractice.movies.domain.model.MovieApiVideosResponse
import com.example.kotlinbestpractice.movies.domain.model.Trailer
import com.example.kotlinbestpractice.movies.domain.repository.MoviesRepository
import com.example.kotlinbestpractice.utils.NetworkResult
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpRequestTimeoutException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/** Created By Ranga
on 04-04-2024
 **/
class MoviesRepoImpl @Inject constructor(private val movieApi: MovieApi,
                                         private val moviesApi: MoviesApiService): MoviesRepository {

    override suspend fun getMovies(year: String): Flow<NetworkResult<MovieApiResponse>> {
       return flow {
           emit(NetworkResult.Loading(true))

           val movieDetailResponseFromApi = try {
               moviesApi.getMovies(year)
           }catch (e: HttpRequestTimeoutException){
               e.printStackTrace()
               emit(NetworkResult.Error(message = "Error loading movies", null))
               return@flow
           }
           emit(
               NetworkResult.Success(
                   movieDetailResponseFromApi.body()
               )
           )
       }
    }


    override suspend fun getMovie(id: String): Flow<NetworkResult<Movie>> {
        return flow {
            emit(NetworkResult.Loading(true))
            val movieDetailResponseFromApi = try {
                moviesApi.getMovie(id)
            }catch (e: Exception){
                e.printStackTrace()
                emit(NetworkResult.Error(message = "Error loading movies", null))
                return@flow
            }
            emit(NetworkResult.Success(movieDetailResponseFromApi.body()))
        }
    }


    override suspend fun getMovieVideos(id: String): Flow<NetworkResult<MovieApiVideosResponse>> {
        return flow {
            emit(NetworkResult.Loading(true))
            val movieDetailResponseFromApi = try {
                moviesApi.getMovieVideos(id)
            }catch (e: Exception){
                e.printStackTrace()
                emit(NetworkResult.Error(message = "Error loading videos", null))
                return@flow
            }
            emit(NetworkResult.Success(movieDetailResponseFromApi.body()))
        }
    }


}