package com.androidchallenge

import com.androidchallenge.data.Exercise
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.http.*

interface BackendService {
    @GET("exercises")
    suspend fun getExercises(): List<Exercise>
}

val API_JSON = Json {
    ignoreUnknownKeys = true
}

val api: BackendService = Retrofit.Builder()
    .baseUrl("https://api.vitruvian.me/v1/")
    .client(OkHttpClient.Builder().build())
    .addConverterFactory(API_JSON.asConverterFactory("application/json".toMediaType()))
    .build()
    .create(BackendService::class.java)