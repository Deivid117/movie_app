package com.dwh.movieapp.utils.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.material.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.dwh.movieapp.ui.theme.NetflixSans

@Composable
fun CustomText(
    modifier: Modifier = Modifier,
    text: String,
    fontWeight: FontWeight = FontWeight.Normal,
    fontSize: TextUnit,
    color: Color = Color.White,
    textAlign: TextAlign = TextAlign.Left
){
    Text(
        modifier = modifier,
        text = text,
        fontFamily = NetflixSans,
        fontWeight = fontWeight,
        fontSize = fontSize,
        textAlign = textAlign,
        color = color
    )
}