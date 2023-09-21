package com.androidchallenge.viewmodel

import androidx.lifecycle.ViewModel
import com.androidchallenge.models.ExerciseListState
import com.androidchallenge.models.mapToState
import com.androidchallenge.repository.MainRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MainViewModel @Inject constructor(
    override val coroutineContext: CoroutineContext,
    private val mainRepository: MainRepository
) : ViewModel(), CoroutineScope {

    private val exercisesStateHolder = StateHolder(createExercisesState())
    val exercisesLiveData = exercisesStateHolder.liveData()

    fun fetchExercises() {
        launch {
            exercisesStateHolder.set(ExerciseListState(exerciseList = mainRepository.getExerciseList().map { it.mapToState() }))
        }
    }

    private fun createExercisesState() = ExerciseListState(
        exerciseList = emptyList()
    )
}