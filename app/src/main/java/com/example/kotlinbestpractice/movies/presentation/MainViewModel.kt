package com.example.kotlinbestpractice.movies.presentation

import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
class MainViewModel @Inject constructor(private val repository: MoviesRepository) : ViewModel() {

    private val _state = MutableStateFlow(MovieScreenState())
    val uiState: StateFlow<MovieScreenState> = _state.asStateFlow()


    var llState: LazyGridState by mutableStateOf( LazyGridState(0,0))

    fun fetchMovies(year: String) {
        viewModelScope.launch {
            repository.getMovies(year).collectLatest { result ->
                when (result) {
                    is NetworkResult.Error -> {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                error = " api failed ${result.message}",
                                data = null
                            )
                        }
                    }
                    is NetworkResult.Loading -> {
                        _state.update {
                            it.copy(
                                isLoading = true,
                                error = " api failed ${result.message}",
                                data = null
                            )
                        }
                    }
                    is NetworkResult.Success -> {
                        result.data?.results?.let { popularList ->
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    data = popularList,
                                    error = null
                                )
                            }
                        }
                    }
                }
            }

        }
    }
}