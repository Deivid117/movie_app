package com.dwh.movieapp.utils.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

@Composable
fun CardFootageImage(imageUrl: String){
    Image(
        modifier = Modifier
            .fillMaxHeight()
            .clip(RoundedCornerShape(15.dp))
            .size(120.dp)
            .background(Color.White),
        alignment = Alignment.Center,
        painter = rememberAsyncImagePainter(
            model = ImageRequest
                .Builder(LocalContext.current)
                .data(imageUrl)
                .build()
        ),
        contentDescription = "",
        contentScale = ContentScale.Crop
    )
}

@Composable
fun DetailsFootageImage(imageUrl: String){
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .size(400.dp)
            .background(Color.White),
        painter = rememberAsyncImagePainter(
            model = ImageRequest
                .Builder(LocalContext.current)
                .data(imageUrl)
                .build()
        ),
        contentDescription = "",
        contentScale = ContentScale.FillBounds
    )
}

@Composable
fun TrendsFootageImage(imageUrl: String, title: String){
    Card(
        modifier = Modifier
            .height(200.dp)
            .width(150.dp),
        shape = RoundedCornerShape(5.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()){
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = rememberAsyncImagePainter(
                    model = ImageRequest
                        .Builder(LocalContext.current)
                        .data(imageUrl)
                        .build()
                ),
                contentDescription = "",
                contentScale = ContentScale.FillBounds
            )
            Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Bottom) {
                CustomText(text = title, fontWeight = FontWeight.Medium, fontSize = 18.sp, textAlign = TextAlign.Center)
            }
        }
    }
}