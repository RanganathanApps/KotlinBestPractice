package com.example.kotlinbestpractice.movies.di

import com.example.kotlinbestpractice.movies.data.remote.MovieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private val httpInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .apply { interceptors().add(httpInterceptor) }
        .build()

    @Provides
    @Singleton
    @Named("MovieRetrofit")
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(MovieApi.BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieApi(@Named("MovieRetrofit") retrofit: Retrofit): MovieApi {
        return retrofit.create(MovieApi::class.java)
    }

}