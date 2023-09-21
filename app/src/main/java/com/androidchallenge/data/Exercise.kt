package com.androidchallenge.data

import com.androidchallenge.utils.toRootLowerCase
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExerciseVideo(
    val id: String,
    val video: String?,
    val thumbnail: String?,
)

@Serializable
enum class EquipmentEnum {
    HANDLES,
    BAR,
    SHORT_BAR,
    STRAPS,
    BELT,
    ROPE,
    BENCH,
    BLACK_CABLES,
    GREY_CABLES,
    FORM_TRAINER;

    fun getReadableName() = name.toRootLowerCase().replace("_", " ")
}

@Serializable
enum class MuscleEnum {
    @SerialName("biceps")
    BICEPS,
    @SerialName("forearms")
    FOREARMS,
    @SerialName("triceps")
    TRICEPS,
    @SerialName("lats")
    LATS,
    @SerialName("lower_back")
    LOWER_BACK,
    @SerialName("traps")
    TRAPS,
    @SerialName("upper_back")
    UPPER_BACK,
    @SerialName("abductors")
    ABDUCTORS,
    @SerialName("calves")
    CALVES,
    @SerialName("glutes")
    GLUTES,
    @SerialName("hamstrings")
    HAMSTRINGS,
    @SerialName("quads")
    QUADS,
    @SerialName("chest")
    CHEST,
    @SerialName("core")
    CORE,
    @SerialName("obliques")
    OBLIQUES,
    @SerialName("shoulders")
    SHOULDERS;

    fun getReadableName() = name.toRootLowerCase().replace("_", " ")
}

@Serializable
data class Exercise(
    val id: String,
    val name: String? = null,
    val videos: List<ExerciseVideo>? = null,
    val equipment: List<EquipmentEnum>? = null,
    val muscles: List<MuscleEnum>? = null,
)

