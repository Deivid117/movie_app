package com.dwh.movieapp.ui.movies.top.rated.movies

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
fun TopRatedMoviesDetailsScreen(
    navController: NavController,
    movieId: String,
    viewModel: TopRatedMovieDetailsViewModel = hiltViewModel()
) {

    val isLoading by remember { viewModel.isLoading }

    if(isLoading) LoadingDialog()

    var isOnline by remember { mutableStateOf(false) }

    BroadcastEventManager(LocalContext.current, ConnectivityManager.CONNECTIVITY_ACTION) {
        val systemResponse = it ?: return@BroadcastEventManager
        isOnline = systemResponse
    }

    LaunchedEffect(isOnline) {
        if(isOnline) viewModel.getTopRatedMovieDetails(movieId.toLong()) else viewModel.getTopRatedMovieDetailsDataBase(movieId.toInt())
    }

    Surface(Modifier.fillMaxSize()) {
        TopRatedMoviesDetailsContent(navController, viewModel, isOnline)
    }
}

@Composable
fun TopRatedMoviesDetailsContent(
    navController: NavController,
    viewModel: TopRatedMovieDetailsViewModel,
    isOnline: Boolean
) {

    val movieDetails by remember { viewModel.topRatedMovieDetails }
    val movieDetailsLocal by remember { viewModel.topRatedMovieDetialsLocal }

    FootageDetails(if(isOnline) movieDetails else movieDetailsLocal, navController)
}

