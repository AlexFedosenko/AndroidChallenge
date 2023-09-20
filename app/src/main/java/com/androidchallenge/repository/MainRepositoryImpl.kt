package com.androidchallenge.repository

import com.androidchallenge.BackendService
import com.androidchallenge.data.Exercise

class MainRepositoryImpl(
    private val backendService: BackendService,
) : MainRepository {
    override suspend fun getExerciseList(): List<Exercise> = backendService.getExercises()
}