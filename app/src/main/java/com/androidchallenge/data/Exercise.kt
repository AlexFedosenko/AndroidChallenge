package com.androidchallenge.data

import kotlinx.serialization.Serializable
import java.time.Duration
import java.time.Instant

@Serializable
data class ExerciseVideo(
    val id: String,
    val video: String?,
    val thumbnail: String?,
)

@Serializable
data class Equipment(
    val id: String?,
    val name: String?,
)

@Serializable
data class MuscleGroup(
    val id: String?,
    val name: String?,
)

@Serializable
data class Exercise(
    val id: String,
    val name: String? = null,
    val videos: List<ExerciseVideo>? = null,
    val equipment: List<Equipment>? = null,
    val muscleGroups: List<MuscleGroup>? = null,
)

