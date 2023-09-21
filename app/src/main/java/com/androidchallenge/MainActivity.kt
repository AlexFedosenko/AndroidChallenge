package com.androidchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.androidchallenge.di.AppComponent
import com.androidchallenge.ui.theme.AndroidChallengeTheme
import com.androidchallenge.ui.widgets.MainWidget
import com.androidchallenge.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels {
        getAppComponent().viewModelsFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidChallengeTheme {
                MainWidget(exercisesLiveDate = viewModel.exercisesLiveData)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.fetchExercises()
    }
}
fun MainActivity.getAppComponent(): AppComponent = (application as AndroidChallengeApp).appComponent
