package com.example.kotlinbestpractice.movies.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinbestpractice.movies.data.repo.MovieListRepoImpl
import com.example.kotlinbestpractice.movies.domain.repository.MoviesRepository
import com.example.kotlinbestpractice.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

/** Created By Ranga
on 03-04-2024
 **/
@HiltViewModel
class MainViewModel @Inject constructor( private val repository: MoviesRepository): ViewModel() {
    val countDownFlow = flow<Int> {
        val start = 10
        var currentValue = start
        emit(start)
        while(currentValue > 0){
            delay(1000L)
            currentValue--
            emit(currentValue)
        }


    }

    init {

       fetchMovies()
    }

    private fun fetchMovies() {
        viewModelScope.launch {
            repository.getPopularMovies().collectLatest { result ->
                when (result) {
                    is NetworkResult.Error ->  println(" api failed ${result.message}")
                    is NetworkResult.Loading ->  println(" api loading ${result.isLoading}")
                    is NetworkResult.Success -> {
                        result.data?.let { popularList ->
                            /* _movieListState.update {
                            it.copy(
                                popularMovieList = movieListState.value.popularMovieList
                                        + popularList.shuffled()
                            )
                        }*/

                            popularList.forEach {
                                println(" api results ${it.title}")
                            }
                        }
                    }
                }
            }

        }
    }
}