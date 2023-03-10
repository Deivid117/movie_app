package com.dwh.movieapp.ui.movies.popular.movies

import android.net.ConnectivityManager
import android.util.Log
import androidx.compose.foundation.layout.*
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
fun PopularMoviesDetailsScreen(
    navController: NavController,
    movieId: String,
    viewModel: PopularMoviesDetailsViewModel = hiltViewModel()
) {

    val isLoading by remember { viewModel.isLoading }

    if(isLoading) LoadingDialog()

    var isOnline by remember { mutableStateOf(false) }

    BroadcastEventManager(LocalContext.current, ConnectivityManager.CONNECTIVITY_ACTION) {
        val systemResponse = it ?: return@BroadcastEventManager
        isOnline = systemResponse
    }

    LaunchedEffect(isOnline) {
        if(isOnline) viewModel.getMovieDetails(movieId.toLong()) else viewModel.getMovieDetailsDataBase(movieId.toInt())
    }

    Surface(Modifier.fillMaxSize()) {
        PopularMoviesDetailsContent(navController, viewModel, isOnline)
    }
}

@Composable
fun PopularMoviesDetailsContent(
    navController: NavController,
    viewModel: PopularMoviesDetailsViewModel,
    isOnline: Boolean
) {

    val movieDetails by remember { viewModel.movieDetails }
    val movieDetailsLocal by remember { viewModel.movieDetailsLocal }

    FootageDetails(if(isOnline) movieDetails else movieDetailsLocal, navController)
}

