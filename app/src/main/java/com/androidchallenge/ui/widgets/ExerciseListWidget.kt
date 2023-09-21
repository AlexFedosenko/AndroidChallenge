package com.androidchallenge.ui.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.LiveData
import com.androidchallenge.models.ExerciseListState
import com.androidchallenge.ui.theme.Dimensions

@Composable
fun ExercisesListWidget(
    modifier: Modifier = Modifier,
    exercisesLiveDate: LiveData<ExerciseListState>
) {
    val exerciseListState by exercisesLiveDate.observeAsState()

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(Dimensions.exerciseListDividerHeight),
    ) {
        if (!exerciseListState?.exerciseList.isNullOrEmpty()) {
            items(exerciseListState!!.exerciseList.size, key = { it }) { index ->
                val exercise = exerciseListState!!.exerciseList[index];
                ExerciseItemWidget(
                    modifier = Modifier,
                    exercise = exercise
                )
            }
        }
    }
}