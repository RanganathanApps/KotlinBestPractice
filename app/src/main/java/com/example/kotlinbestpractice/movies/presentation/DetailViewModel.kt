package com.example.kotlinbestpractice.movies.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinbestpractice.movies.domain.model.Movie
import com.example.kotlinbestpractice.movies.domain.repository.MoviesRepository
import com.example.kotlinbestpractice.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/** Created By Ranga
on 03-04-2024
 **/
@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: MoviesRepository) : ViewModel() {

    private val _state = MutableStateFlow(MovieScreenState())
    val uiState: StateFlow<MovieScreenState> = _state.asStateFlow()

    lateinit var data: Flow<NetworkResult<List<Movie>>>
    fun getMovieById(id: String){
        viewModelScope.launch {
            repository.getMovie(id).collectLatest { result ->
                when(result){
                    is NetworkResult.Loading -> {
                        _state.update {
                            it.copy(
                                isLoading = true,
                                movie = null,
                                error = null
                            )
                        }
                    }
                    is NetworkResult.Success -> {
                        result.data?.let { movie ->
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    movie = movie,
                                    error = null
                                )
                            }
                        }
                    }
                    is NetworkResult.Error -> {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                trailers = null,
                                error = "failed to load Movie Detail"
                            )
                        }
                    }
                }
            }
        }
    }


    fun getMovieTrailerVideos(id: String){
        viewModelScope.launch {
            repository.getMovieVideos(id).collectLatest { result ->
                when(result){
                    is NetworkResult.Loading -> {
                        _state.update {
                            it.copy(
                                isLoading = true,
                                trailers = null,
                                error = null
                            )
                        }
                    }
                    is NetworkResult.Success -> {
                        result.data?.results?.let { trailers ->
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    trailers = trailers,
                                    error = null
                                )
                            }
                        }
                    }
                    is NetworkResult.Error -> {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                trailers = null,
                                error = "failed to load trailers"
                            )
                        }
                    }
                }
            }
        }
    }
}