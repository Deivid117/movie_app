package com.dwh.movieapp.ui.trends

import android.net.ConnectivityManager
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Observer
import com.dwh.domain.models.entities.TrendEntity
import com.dwh.domain.models.get.trends.Trends
import com.dwh.domain.models.get.trends.TrendsResults
import com.dwh.movieapp.utils.broadcast.BroadcastEventManager
import com.dwh.movieapp.utils.utils.EmptyData
import com.dwh.movieapp.utils.utils.TrendsFootageImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import mx.com.satoritech.web.APIConstants

@Composable
fun TrendsCarousel(viewModel: TrendsViewModel = hiltViewModel()) {

    var isOnline by remember { mutableStateOf(false) }

    BroadcastEventManager(LocalContext.current, ConnectivityManager.CONNECTIVITY_ACTION) {
        val systemResponse = it ?: return@BroadcastEventManager
        isOnline = systemResponse
    }

    LaunchedEffect(isOnline){
        if(isOnline) viewModel.getTrends("all", "day") else viewModel.getTrendsDataBase()
    }

    TrendsCarouselContent(viewModel, isOnline)
}

@Composable
fun TrendsCarouselContent(
    viewModel: TrendsViewModel,
    isOnline: Boolean
){

    val trendList by remember { viewModel.trends }

    var trendListLocal by remember { mutableStateOf<List<TrendEntity>>(listOf()) }

    val owner = LocalLifecycleOwner.current
    viewModel.trendListLocal.observe(owner, Observer { list ->
        trendListLocal = list
    })

    CarouselContainer(if(isOnline) trendList.results else trendListLocal)
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CarouselContainer(trendsList: List<Any>) {

    if(trendsList.isNotEmpty()) {

        val pagerState = rememberPagerState(pageCount = trendsList.size)

        LaunchedEffect(Unit) {
            while (true) {
                Thread.yield()
                delay(2000)
                if (pagerState.pageCount != 0) {
                    pagerState.animateScrollToPage(
                        page = (pagerState.currentPage + 1) % (pagerState.pageCount),
                        animationSpec = tween(2000)
                    )
                }
            }
        }

        Box(Modifier.fillMaxWidth()) {
            HorizontalPager(
                state = pagerState,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxSize(),
                dragEnabled = false,
                itemSpacing = 8.dp
            ) { page ->
                CarouselPage(trendsResults = trendsList[page])
            }
        }
    } else {
        EmptyData()
    }
}

@Composable
fun CarouselPage(trendsResults: Any) {

    var imageUrl = ""
    var title = ""

    if(trendsResults is TrendsResults) {
        imageUrl = trendsResults.backdropPath ?: ""
        title = trendsResults.title ?: ""
    } else if(trendsResults is TrendEntity) {
        imageUrl = trendsResults.backdropPath ?: ""
        title = trendsResults.title ?: ""
    }

    if(imageUrl.isNotEmpty()) {
        TrendsFootageImage(APIConstants.SERVER_IMAGE_URL + imageUrl, title)
    } else {
        TrendsFootageImage("https://picsum.photos/150/200", title)
    }
}