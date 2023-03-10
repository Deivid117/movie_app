package com.dwh.movieapp.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.dwh.movieapp.R


val NetflixSans = FontFamily(
    Font(R.font.netflixsans_bold, FontWeight.Bold),
    Font(R.font.netflixsans_medium, FontWeight.Medium),
    Font(R.font.netflixsans_regular, FontWeight.Normal),
    Font(R.font.netflixsans_light, FontWeight.Light),
)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = NetflixSans,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
)
