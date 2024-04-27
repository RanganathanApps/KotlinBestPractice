package com.example.kotlinbestpractice.movies.di

import com.example.kotlinbestpractice.movies.data.repository.MoviesRepoImpl
import com.example.kotlinbestpractice.movies.domain.repository.MoviesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMoviesRepo(moviesRepoImpl: MoviesRepoImpl): MoviesRepository
}