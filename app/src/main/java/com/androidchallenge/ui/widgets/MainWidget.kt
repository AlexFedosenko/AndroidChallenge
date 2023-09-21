package com.androidchallenge.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.LiveData
import com.androidchallenge.R
import com.androidchallenge.models.ExerciseListState
import com.androidchallenge.ui.theme.Dimensions
import com.androidchallenge.ui.theme.lightGrey
import com.androidchallenge.ui.theme.typography

@Composable
fun MainWidget(
    exercisesLiveDate: LiveData<ExerciseListState>
) {
    Column(
        modifier = Modifier
            .background(color = lightGrey)
            .padding(Dimensions.exerciseListPadding)
    ) {
        Text(
            modifier = Modifier
                .padding(bottom = Dimensions.exerciseListPadding)
                .wrapContentSize(),
            text = stringResource(id = R.string.exercises_label),
            style = typography.h5
        )
        ExercisesListWidget(
            modifier = Modifier
                .fillMaxSize(),
            exercisesLiveDate = exercisesLiveDate
        )
    }
}
