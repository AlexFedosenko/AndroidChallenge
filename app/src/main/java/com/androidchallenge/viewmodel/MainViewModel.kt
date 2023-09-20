package com.androidchallenge.viewmodel

import androidx.lifecycle.ViewModel
import com.androidchallenge.repository.MainRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MainViewModel @Inject constructor(
    override val coroutineContext: CoroutineContext,
    private val mainRepository: MainRepository
) : ViewModel(), CoroutineScope {

    fun getExercises() {
        launch {
            println(mainRepository.getExerciseList())
        }
    }

}