package com.androidchallenge.di

import com.androidchallenge.viewmodel.ViewModelFactory
import dagger.Component

@Component
interface AppComponent {

    fun viewModelsFactory(): ViewModelFactory


}