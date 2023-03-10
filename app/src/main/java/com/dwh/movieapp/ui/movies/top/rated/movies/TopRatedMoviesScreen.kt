package com.dwh.movieapp.ui.movies.top.rated.movies

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
import com.dwh.domain.models.entities.TopRatedMoviesEntity
import com.dwh.domain.models.entities.TopRatedSeriesEntity
import com.dwh.movieapp.R
import com.dwh.movieapp.composables.LoadingDialog
import com.dwh.movieapp.ui.series.top.rated.series.TopRatedSeriesViewModel
import com.dwh.movieapp.ui.theme.DarkBlueBackground
import com.dwh.movieapp.utils.broadcast.BroadcastEventManager
import com.dwh.movieapp.utils.utils.FootageList
import com.dwh.movieapp.utils.utils.TopAppBar

@Composable
fun TopRatedSeriesScreen(
    navController: NavController,
    viewModel: TopRatedSeriesViewModel = hiltViewModel()
) {

    val isLoading by remember { viewModel.isLoading }

    if(isLoading) LoadingDialog()

    var isOnline by remember { mutableStateOf(false) }

    BroadcastEventManager(LocalContext.current, ConnectivityManager.CONNECTIVITY_ACTION) {
        val systemResponse = it ?: return@BroadcastEventManager
        isOnline = systemResponse
    }

    LaunchedEffect(isOnline){
        if(isOnline) viewModel.getTopRatedSeries() else viewModel.getTopRatedMoviesDataBase()
    }

    Surface(Modifier.fillMaxSize()) {
        TopAppBar(
            showBackIcon = true,
            navController = navController
        ) {
            TopRatedSeriesContent(navController, viewModel, isOnline)
        }
    }
}

@Composable
fun TopRatedSeriesContent(
    navController: NavController,
    viewModel: TopRatedSeriesViewModel,
    isOnline: Boolean
) {

    val topRatedSeriesList by remember { viewModel.topRatedSeries }

    var topRatedSeriesListLocal by remember { mutableStateOf<List<TopRatedSeriesEntity>>(listOf()) }

    val owner = LocalLifecycleOwner.current
    viewModel.topRatedSeriesListLocal.observe(owner, Observer { list ->
        topRatedSeriesListLocal = list
    })

    Column(
        Modifier
            .background(DarkBlueBackground)
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
    ) {
        FootageList(
            navController,
            stringResource(id = R.string._top_rated_series),
            if(isOnline) ArrayList(topRatedSeriesList.results) else ArrayList(topRatedSeriesListLocal)
        )
    }
}
