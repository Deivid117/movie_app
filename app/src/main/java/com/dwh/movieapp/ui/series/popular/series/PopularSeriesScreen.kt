package com.dwh.movieapp.ui.series.popular.series

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
import com.dwh.domain.models.entities.SerieEntity
import com.dwh.movieapp.R
import com.dwh.movieapp.composables.LoadingDialog
import com.dwh.movieapp.ui.theme.DarkBlueBackground
import com.dwh.movieapp.utils.broadcast.BroadcastEventManager
import com.dwh.movieapp.utils.utils.FootageList
import com.dwh.movieapp.utils.utils.TopAppBar

@Composable
fun PopularSeriesScreen(
    navController: NavController,
    viewModel: PopularSeriesViewModel = hiltViewModel()
) {

    val isLoading by remember { viewModel.isLoading }

    if(isLoading) LoadingDialog()

    var isOnline by remember { mutableStateOf(false) }

    BroadcastEventManager(LocalContext.current, ConnectivityManager.CONNECTIVITY_ACTION) {
        val systemResponse = it ?: return@BroadcastEventManager
        isOnline = systemResponse
    }

    LaunchedEffect(isOnline){
        if(isOnline) viewModel.getPopularSeries() else viewModel.getSeriesDataBase()
    }

    Surface(Modifier.fillMaxSize()) {
        TopAppBar(
            showBackIcon = true,
            navController = navController
        ) {
            PopularSeriesContent(navController, viewModel, isOnline)
        }
    }
}

@Composable
fun PopularSeriesContent(
    navController: NavController,
    viewModel: PopularSeriesViewModel,
    isOnline: Boolean
) {

    val popularSeriesList by remember { viewModel.series }

    var popularSeriesListLocal by remember { mutableStateOf<List<SerieEntity>>(listOf()) }

    val owner = LocalLifecycleOwner.current
    viewModel.seriesListLocal.observe(owner, Observer { list ->
        popularSeriesListLocal = list
    })

    Column(
        Modifier
            .background(DarkBlueBackground)
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
    ) {
        FootageList(
            navController,
            stringResource(id = R.string.series),
            if(isOnline) ArrayList(popularSeriesList.results) else ArrayList(popularSeriesListLocal)
        )
    }
}
