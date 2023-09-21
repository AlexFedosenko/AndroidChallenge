package com.androidchallenge.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.androidchallenge.models.ExerciseItemState
import com.androidchallenge.ui.theme.Dimensions
import com.androidchallenge.ui.theme.shapes
import com.androidchallenge.ui.theme.teal200
import com.androidchallenge.ui.theme.typography
import com.androidchallenge.ui.theme.white
import com.androidchallenge.utils.toRootLowerCase

private const val ID_IMAGE = 1
private const val ID_TITLE = 2
private const val ID_EQUIPMENT_ICON = 3
private const val ID_EQUIPMENT = 4
private const val ID_MUSCLE_ICON = 5
private const val ID_MUSCLE = 6


@Preview
@Composable
fun ExerciseItemWidgetPreview() {
    ExerciseItemWidget(
        exercise = ExerciseItemState(
            imageUrl = "",
            exerciseName = "Bench Press",
            equipmentList = listOf("Bench"),
            muscleList = listOf("Core, biceps, triceps, upper back")
        )
    )
}

@Composable
fun ExerciseItemWidget(
    modifier: Modifier = Modifier,
    exercise: ExerciseItemState,
) {

    val constraintSet = ConstraintSet {

        val image = createRefFor(ID_IMAGE)
        val titleText = createRefFor(ID_TITLE)
        val equipmentIcon = createRefFor(ID_EQUIPMENT_ICON)
        val equipmentText = createRefFor(ID_EQUIPMENT)
        val muscleIcon = createRefFor(ID_MUSCLE_ICON)
        val muscleText = createRefFor(ID_MUSCLE)

        constrain(image) {
            start.linkTo(parent.start)
            bottom.linkTo(parent.bottom)
            top.linkTo(parent.top)
            height = Dimension.wrapContent
            width = Dimension.wrapContent
        }

        constrain(titleText) {
            start.linkTo(image.end, margin = Dimensions.paddingMedium)
            top.linkTo(parent.top)
            bottom.linkTo(equipmentIcon.top)
            end.linkTo(parent.end, margin = Dimensions.paddingMedium)
            height = Dimension.wrapContent
            width = Dimension.fillToConstraints
        }

        constrain(equipmentIcon) {
            start.linkTo(titleText.start)
            top.linkTo(equipmentIcon.bottom)
            bottom.linkTo(parent.bottom)
            end.linkTo(equipmentText.start, margin = Dimensions.paddingMedium)
            width = Dimension.wrapContent
            height = Dimension.wrapContent
        }

        constrain(equipmentText) {
            start.linkTo(equipmentIcon.end)
            top.linkTo(equipmentIcon.top)
            bottom.linkTo(equipmentIcon.bottom)
            end.linkTo(muscleIcon.start, margin = Dimensions.paddingMedium)
            width = Dimension.wrapContent
            height = Dimension.wrapContent
        }

        constrain(muscleIcon) {
            start.linkTo(equipmentText.end)
            top.linkTo(equipmentText.top)
            bottom.linkTo(equipmentText.bottom)
            end.linkTo(muscleText.start, margin = Dimensions.paddingMedium)
            width = Dimension.wrapContent
            height = Dimension.wrapContent
        }

        constrain(muscleText) {
            start.linkTo(muscleIcon.end)
            top.linkTo(muscleIcon.top)
            bottom.linkTo(muscleIcon.bottom)
            end.linkTo(parent.end, margin = Dimensions.paddingMedium)
            width = Dimension.fillToConstraints
            height = Dimension.wrapContent
        }

        createVerticalChain(
            titleText, equipmentIcon,
            chainStyle = ChainStyle.Packed
        )
    }

    ConstraintLayout(
        constraintSet = constraintSet,
        modifier = modifier
            .background(white)
            .wrapContentHeight()
            .fillMaxWidth()
            .clip(shapes.medium)
    ) {
        AsyncImage(
            modifier = modifier
                .layoutId(ID_IMAGE)
                .size(Dimensions.exerciseThumbnailSize),
            model = ImageRequest.Builder(LocalContext.current)
                .data(exercise.imageUrl)
                .crossfade(true)
                .placeholder(exercise.placeholderResId)
                .error(exercise.errorResId)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
        )

        Text(
            modifier = modifier
                .layoutId(ID_TITLE)
                .fillMaxWidth()
                .wrapContentHeight(),
            text = exercise.exerciseName,
            textAlign = TextAlign.Start,
            style = typography.h6,
            maxLines = 2
        )

        Icon(
            modifier = modifier
                .layoutId(ID_EQUIPMENT_ICON)
                .width(Dimensions.exerciseIconSize)
                .height(Dimensions.exerciseIconSize),
            imageVector = ImageVector.vectorResource(id = exercise.equipmentResId),
            contentDescription = null,
            tint = teal200
        )

        Text(
            modifier = modifier
                .layoutId(ID_EQUIPMENT)
                .wrapContentSize(),
            text = exercise.equipmentList.joinToString(separator = ", ").toRootLowerCase(),
            style = typography.subtitle1,
        )

        Icon(
            modifier = modifier
                .layoutId(ID_MUSCLE_ICON)
                .width(Dimensions.exerciseIconSize)
                .height(Dimensions.exerciseIconSize),
            imageVector = ImageVector.vectorResource(id = exercise.muscleResId),
            contentDescription = null,
            tint = teal200
        )

        Text(
            modifier = modifier
                .layoutId(ID_MUSCLE),
            text = exercise.muscleList.joinToString(separator = ", "),
            maxLines = 1,
            style = typography.subtitle1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}
