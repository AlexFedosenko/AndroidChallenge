package com.androidchallenge.repository

import com.androidchallenge.data.Exercise

interface MainRepository {

    suspend fun getExerciseList(): List<Exercise>
}