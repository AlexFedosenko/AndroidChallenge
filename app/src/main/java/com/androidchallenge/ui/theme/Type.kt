package com.androidchallenge.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    h3 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontStyle = FontStyle.Normal,
        fontSize = 30.sp,
        lineHeight = 46.sp,
    ),

    h5 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal,
        fontSize = 20.sp,
        lineHeight = 30.sp,
    ),

    h6 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal,
        fontSize = 16.sp,
        lineHeight = 22.sp,
    ),

    subtitle1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Normal,
        fontSize = 12.sp,
        lineHeight = 20.sp,
    )
)