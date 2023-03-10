package com.dwh.movieapp.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dwh.movieapp.R
import com.dwh.movieapp.ui.movies.upcoming.movies.UpComingMovies
import com.dwh.movieapp.ui.theme.DarkBlueBackground
import com.dwh.movieapp.ui.trends.TrendsCarousel
import com.dwh.movieapp.utils.utils.CustomText
import com.dwh.movieapp.utils.utils.TopAppBar

@Composable
fun HomeScreen(
    navController: NavController
) {
    Surface(Modifier.fillMaxSize()) {
        TopAppBar(
            navController = navController
        ){
            HomeContent(navController)
        }
    }
}

@Composable
fun HomeContent(
    navController: NavController
) {
    Column(
        Modifier
            .fillMaxWidth()
            .background(DarkBlueBackground)
            .padding(horizontal = 15.dp)
            .verticalScroll(rememberScrollState())
    ){
        Spacer(modifier = Modifier.height(15.dp))
        CustomText(
            text = stringResource(R.string.trendings),
            fontWeight = FontWeight.Medium,
            fontSize = 30.sp
        )
        Spacer(modifier = Modifier.height(10.dp))
        TrendsCarousel()
        Spacer(modifier = Modifier.height(30.dp))
        CustomText(
            text = stringResource(R.string.upcoming_movies),
            fontWeight = FontWeight.Medium,
            fontSize = 30.sp
        )
        Spacer(modifier = Modifier.height(10.dp))
        UpComingMovies(navController)
    }
}

