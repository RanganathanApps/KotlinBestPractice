package com.example.kotlinbestpractice.movies.data.network

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.path

/** Created By Ranga
on 20-04-2024
 **/
class MoviesApiService(private val client: HttpClient) {

    suspend fun getMovies(year: String): HttpResponse {
        return client.get(HttpRoutes.MOVIES) {
            url {
                parameters.append("api_key", HttpRoutes.API_KEY)
                parameters.append("year", year)
                parameters.append("with_original_language", "ta")
            }
        }

    }
    suspend fun getMovie(id: String): HttpResponse {
        return client.get(HttpRoutes.MOVIE+id) {
            url {
                parameters.append("api_key", HttpRoutes.API_KEY)
            }
        }

    }
    suspend fun getMovieVideos(id: String): HttpResponse {
        return client.get(HttpRoutes.MOVIE_TRAILERS) {
            url {
                parameters.append("api_key", HttpRoutes.API_KEY)
                path("3/movie/$id/videos")
            }
        }

    }
}