package com.androidchallenge.di

import com.androidchallenge.viewmodel.ViewModelFactory
import dagger.Component

@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {

    fun viewModelsFactory(): ViewModelFactory


}