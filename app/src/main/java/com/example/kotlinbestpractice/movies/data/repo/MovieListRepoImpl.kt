package com.example.kotlinbestpractice.movies.data.repo

import com.example.kotlinbestpractice.movies.data.remote.MovieApi
import com.example.kotlinbestpractice.movies.domain.model.Movie
import com.example.kotlinbestpractice.movies.domain.repository.MoviesRepository
import com.example.kotlinbestpractice.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/** Created By Ranga
on 04-04-2024
 **/
class MovieListRepoImpl @Inject constructor(private val movieApi: MovieApi): MoviesRepository {
    override fun getPopularMovies():
            Flow<NetworkResult<List<Movie>>> {
       return flow {
           emit(NetworkResult.Loading(true))
           val moviesResponseFromApi = try {
               movieApi.getPopularMovies()
           }catch (e: IOException){
               e.printStackTrace()
               emit(NetworkResult.Error(message = "Error loading movies", null))
               return@flow
           }catch (e: HttpException){
               e.printStackTrace()
               emit(NetworkResult.Error(message = "Error loading movies", null))
               return@flow
           }catch (e: Exception){
               e.printStackTrace()
               emit(NetworkResult.Error(message = "Error loading movies", null))
               return@flow
           }
           emit(NetworkResult.Success(moviesResponseFromApi.results.map {
                it
           }))

           emit(NetworkResult.Loading(false))

       }
    }

    override suspend fun getMovies(
        forceFetchFromRemote: Boolean,
        category: String,
        page: Int
    ): Flow<NetworkResult<List<Movie>>> {
        return flow {
            //emit(NetworkResult.Success(listOf(Movie()))
        }
    }

    override suspend fun getMovie(id: Int): Flow<NetworkResult<Movie>> {
       return flow {  }
    }

}