package com.dwh.movieapp.utils.utils

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.dwh.movieapp.R

@Composable
fun EmptyData(){
    Column(Modifier.fillMaxWidth()) {
        CustomText(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            text = stringResource(R.string.no_elements),
            fontSize = 18.sp
        )
    }
}