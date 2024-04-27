package com.example.kotlinbestpractice.movies.data.network

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.accept
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

/** Created By Ranga
on 20-04-2024
 **/
private const val TIME_OUT = 6000L

@OptIn(ExperimentalSerializationApi::class)
val httpClientAndroid = HttpClient(Android){

    /*//config Client Serialization
    install(JsonFeature) {
        serializer = KotlinxSerializer(json)
    }*/

    install(ContentNegotiation){
        json(
            Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
                explicitNulls = false
                useAlternativeNames = true
                encodeDefaults = false
            }
        )
    }

    install(HttpTimeout){
        socketTimeoutMillis = TIME_OUT
        connectTimeoutMillis = TIME_OUT
        requestTimeoutMillis = TIME_OUT
    }
    install(Logging){
        level = LogLevel.ALL
    }
    install(ResponseObserver){
        onResponse {
            Log.d("response status",it.status.value.toString())
        }
    }
    install(DefaultRequest){
        header(HttpHeaders.ContentType,ContentType.Application.Json)
    }

    defaultRequest {
        contentType(ContentType.Application.Json)
        accept(ContentType.Application.Json)
    }
}