package com.dwh.movieapp.ui.movies.popular.movies

import android.graphics.Movie
import android.net.ConnectivityManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import com.dwh.domain.models.entities.MovieEntinty
import com.dwh.domain.models.entities.TrendEntity
import com.dwh.movieapp.R
import com.dwh.movieapp.composables.LoadingDialog
import com.dwh.movieapp.utils.broadcast.BroadcastEventManager
import com.dwh.movieapp.ui.theme.DarkBlueBackground
import com.dwh.movieapp.utils.utils.FootageList
import com.dwh.movieapp.utils.utils.TopAppBar

@Composable
fun PopularMoviesScreen(
    navController: NavController,
    viewModel: PopularMoviesViewModel = hiltViewModel()
) {

    val isLoading by remember { viewModel.isLoading }

    if(isLoading) LoadingDialog()

    var isOnline by remember { mutableStateOf(false) }

    BroadcastEventManager(LocalContext.current, ConnectivityManager.CONNECTIVITY_ACTION) {
        val systemResponse = it ?: return@BroadcastEventManager
        isOnline = systemResponse
    }

    LaunchedEffect(isOnline){
        if(isOnline) viewModel.getPopularMovies() else viewModel.getPopularMoviesDataBase()
    }

    Surface(Modifier.fillMaxSize()) {
        TopAppBar(
            showBackIcon = true,
            navController = navController
        ) {
            PopularMoviesContent(navController, viewModel, isOnline)
        }
    }
}

@Composable
fun PopularMoviesContent(
    navController: NavController,
    viewModel: PopularMoviesViewModel,
    isOnline: Boolean
) {

    val popularMoviesList by remember { viewModel.movies }

    var popularMoviesListLocal by remember { mutableStateOf<List<MovieEntinty>>(listOf()) }

    val owner = LocalLifecycleOwner.current
    viewModel.movieListLocal.observe(owner, Observer { list ->
        popularMoviesListLocal = list
    })

    Column(
        Modifier
            .background(DarkBlueBackground)
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
    ) {
        FootageList(
            navController,
            stringResource(R.string._movies),
            if(isOnline) ArrayList(popularMoviesList.results) else ArrayList(popularMoviesListLocal)
        )
    }
}
