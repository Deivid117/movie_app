package com.dwh.movieapp.composables

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.*
import com.dwh.movieapp.R

@Composable
fun LoadingDialog() {

    val isLottiePlaying by remember {
        mutableStateOf(true)
    }

    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.loading)
    )

    val lottieAnimation by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = isLottiePlaying
    )

    Dialog(onDismissRequest = {}) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CompositionAnimation(composition, lottieAnimation, 100)
        }
    }
}

@Composable()
fun CompositionAnimation(
    composition: LottieComposition?,
    lottieAnimation: Float,
    size: Int
){
    LottieAnimation(
        composition,
        lottieAnimation,
        Modifier.size(size.dp)
    )
}