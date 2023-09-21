package com.androidchallenge.models

import com.androidchallenge.data.Exercise

/**
 * Only the first video thumbnail will be used as an exercise depiction image
 * Could be updated further to make an animation for a list of thumbnails
 */
fun Exercise.mapToState() = ExerciseItemState(
    imageUrl = videos?.firstOrNull()?.thumbnail.orEmpty(),
    exerciseName = name.orEmpty(),
    equipmentList = equipment?.map {
        it.getReadableName()
    } ?: emptyList(),
    muscleList = muscles?.map {
        it.getReadableName()
    } ?: emptyList()
)