package com.dwh.movieapp.ui.series.top.rated.series

import android.net.ConnectivityManager
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dwh.movieapp.composables.LoadingDialog
import com.dwh.movieapp.utils.broadcast.BroadcastEventManager
import com.dwh.movieapp.utils.utils.FootageDetails

@Composable
fun TopRatedSeriesDetailsScreen(
    navController: NavController,
    tvId: String,
    viewModel: TopRatedSerieDetailsViewModel = hiltViewModel()
) {

    val isLoading by remember { viewModel.isLoading }

    if(isLoading) LoadingDialog()

    var isOnline by remember { mutableStateOf(false) }

    BroadcastEventManager(LocalContext.current, ConnectivityManager.CONNECTIVITY_ACTION) {
        val systemResponse = it ?: return@BroadcastEventManager
        isOnline = systemResponse
    }

    LaunchedEffect(isOnline) {
        if(isOnline) viewModel.getTopRatedSerieDetails(tvId.toLong()) else viewModel.getTopRatedMovieDetailsDataBase(tvId.toInt())
    }

    Surface(Modifier.fillMaxSize()) {
        TopRatedSeriesDetailsContent(navController, viewModel, isOnline)
    }
}

@Composable
fun TopRatedSeriesDetailsContent(
    navController: NavController,
    viewModel: TopRatedSerieDetailsViewModel,
    isOnline: Boolean
) {

    val movieDetails by remember { viewModel.topRatedSerieDetails }
    val movieDetailsLocal by remember { viewModel.topRatedSerieDetialsLocal }

    FootageDetails(if(isOnline) movieDetails else movieDetailsLocal, navController)
}

