package com.dwh.movieapp.ui.movies.upcoming.movies

import android.net.ConnectivityManager
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import com.dwh.domain.models.entities.UpcomingMoviesEntity
import com.dwh.movieapp.composables.LoadingDialog
import com.dwh.movieapp.navigation.Screens
import com.dwh.movieapp.utils.broadcast.BroadcastEventManager
import com.dwh.movieapp.utils.utils.EmptyData
import com.dwh.movieapp.utils.utils.FootageCard

@Composable
fun UpComingMovies(
    navController: NavController,
    viewModel: UpcomingMoviesViewModel = hiltViewModel()
) {

    val isLoading by remember { viewModel.isLoading }

    if(isLoading) LoadingDialog()

    var isOnline by remember { mutableStateOf(false) }

    BroadcastEventManager(LocalContext.current, ConnectivityManager.CONNECTIVITY_ACTION) {
        val systemResponse = it ?: return@BroadcastEventManager
        isOnline = systemResponse
    }


    LaunchedEffect(isOnline){
        if(isOnline) viewModel.getUpcomingMovies() else viewModel.getUpcomingMoviesDataBase()
    }

    UpcomingMoviesContent(viewModel, navController,isOnline)

}

@Composable
fun UpcomingMoviesContent(
    viewModel: UpcomingMoviesViewModel,
    navController: NavController,
    isOnline: Boolean
) {
    val upcomingMoviesList by remember { viewModel.upcomingMovies }

    var upcomingMoviesListLocal by remember { mutableStateOf<List<UpcomingMoviesEntity>>(listOf()) }

    val owner = LocalLifecycleOwner.current
    viewModel.upcomingMovieListLocal.observe(owner, Observer { list ->
        upcomingMoviesListLocal = list
    })

    UpcomingMoviesList(if(isOnline) upcomingMoviesList.results else upcomingMoviesListLocal, navController)
}

@Composable
fun UpcomingMoviesList(
    upcomingMoviesList: List<Any>,
    navController: NavController
) {
    val notClickable = true

    if (upcomingMoviesList.isNotEmpty()) {
        upcomingMoviesList.subList(0, 5).forEach {
            FootageCard(navController, it, Screens.POPULAR_SERIES_SCREEN, notClickable)
            Spacer(modifier = Modifier.height(12.dp))
        }
    } else {
        EmptyData()
    }
}

