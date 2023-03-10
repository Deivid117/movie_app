package com.dwh.movieapp.ui.series.top.rated.series

import android.net.ConnectivityManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.dwh.domain.models.entities.TopRatedMoviesEntity
import com.dwh.movieapp.R
import com.dwh.movieapp.composables.LoadingDialog
import com.dwh.movieapp.ui.movies.top.rated.movies.TopRatedMoviesViewModel
import com.dwh.movieapp.ui.theme.DarkBlueBackground
import com.dwh.movieapp.utils.broadcast.BroadcastEventManager
import com.dwh.movieapp.utils.utils.FootageList
import com.dwh.movieapp.utils.utils.TopAppBar

@Composable
fun TopRatedMoviesScreen(
    navController: NavController,
    viewModel: TopRatedMoviesViewModel = hiltViewModel()
) {

    val isLoading by remember { viewModel.isLoading }

    if(isLoading) LoadingDialog()

    var isOnline by remember { mutableStateOf(false) }

    BroadcastEventManager(LocalContext.current, ConnectivityManager.CONNECTIVITY_ACTION) {
        val systemResponse = it ?: return@BroadcastEventManager
        isOnline = systemResponse
    }

    LaunchedEffect(isOnline){
        if(isOnline) viewModel.getTopRatedMovies() else viewModel.getTopRatedMoviesDataBase()
    }

    Surface(Modifier.fillMaxSize()) {
        TopAppBar(
            showBackIcon = true,
            navController = navController
        ) {
            TopRatedMoviesContent(navController, viewModel, isOnline)
        }
    }
}

@Composable
fun TopRatedMoviesContent(
    navController: NavController,
    viewModel: TopRatedMoviesViewModel,
    isOnline: Boolean
) {

    val topRatedMoviesList by remember { viewModel.topRatedMovies }

    var topRatedMoviesListLocal by remember { mutableStateOf<List<TopRatedMoviesEntity>>(listOf()) }

    val owner = LocalLifecycleOwner.current
    viewModel.topRatedMoviesListLocal.observe(owner, Observer { list ->
        topRatedMoviesListLocal = list
    })

    Column(
        Modifier
            .background(DarkBlueBackground)
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
    ) {
        FootageList(
            navController,
            stringResource(id = R.string._top_rated_movies),
            if(isOnline) ArrayList(topRatedMoviesList.results) else ArrayList(topRatedMoviesListLocal)
        )
    }
}
