package com.androidchallenge

import android.app.Application
import com.androidchallenge.di.AppComponent
import com.androidchallenge.di.DaggerAppComponent

class AndroidChallengeApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init () {
        appComponent = DaggerAppComponent
            .builder()
            // could be injected more dependencies
            .build()

    }
}