package com.dwh.movieapp.ui.series.popular.series

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
fun PopularSeriesDetailsScreen(
    navController: NavController,
    tvId: String,
    viewModel: PopularSeriesDetailsViewModel = hiltViewModel()
) {

    val isLoading by remember { viewModel.isLoading }

    if(isLoading) LoadingDialog()

    var isOnline by remember { mutableStateOf(false) }

    BroadcastEventManager(LocalContext.current, ConnectivityManager.CONNECTIVITY_ACTION) {
        val systemResponse = it ?: return@BroadcastEventManager
        isOnline = systemResponse
    }

    LaunchedEffect(isOnline) {
        if(isOnline) viewModel.getSerieDetails(tvId.toLong()) else viewModel.getSerieDetailsDataBase(tvId.toInt())
    }

    Surface(Modifier.fillMaxSize()) {
        PopularSeriesDetailsContent(navController, viewModel, isOnline)
    }
}

@Composable
fun PopularSeriesDetailsContent(
    navController: NavController,
    viewModel: PopularSeriesDetailsViewModel,
    isOnline: Boolean
) {

    val serieDetails by remember { viewModel.serieDetails }
    val serieDetailsLocal by remember { viewModel.serieDetailsLocal }

    FootageDetails(if(isOnline) serieDetails else serieDetailsLocal, navController)
}

