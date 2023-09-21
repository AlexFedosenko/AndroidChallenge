package com.androidchallenge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class StateHolder<T>(initializerState: T) {
    private var state: T = initializerState
        set(value) {
            field = value
            _stateLiveData.postValue(value)
        }

    private val _stateLiveData = MutableLiveData(state)

    private val stateLiveData: LiveData<T> = _stateLiveData

    fun get() = state

    fun set(state: T) {
        this.state = state
    }

    fun liveData() = stateLiveData

}
