package com.androidchallenge.models

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.androidchallenge.R
import com.androidchallenge.ui.theme.teal200

data class ExerciseItemState(
    val imageUrl: String,
    val exerciseName: String,
    val equipmentList: List<String>,
    val muscleList: List<String>,
    @DrawableRes val equipmentResId: Int = R.drawable.ic_equipment,
    @DrawableRes val muscleResId: Int = R.drawable.ic_muscle,
    @DrawableRes val placeholderResId: Int = R.drawable.ic_placeholder,
    @DrawableRes val errorResId: Int = R.drawable.ic_error,
    @ColorRes val iconColorResId: Color = teal200,
)
