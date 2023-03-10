package com.dwh.movieapp.utils.utils

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dwh.domain.models.entities.MovieEntinty
import com.dwh.domain.models.entities.SerieEntity
import com.dwh.domain.models.entities.TopRatedMoviesEntity
import com.dwh.domain.models.entities.TopRatedSeriesEntity
import com.dwh.domain.models.get.movies.popular.upcoming.MovieResults
import com.dwh.domain.models.get.series.popular.upcoming.SerieResults
import com.dwh.movieapp.navigation.Screens

@Composable
fun FootageList(
    navController: NavController,
    title: String,
    footageList: ArrayList<Any>,
) {
    Column(Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(15.dp))
        CustomText(text = title, fontWeight = FontWeight.Medium, fontSize = 30.sp)
        Spacer(modifier = Modifier.height(20.dp))

        if(footageList.isNotEmpty()) {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(footageList) {
                    when (footageList.firstOrNull()) {
                        is MovieResults -> {
                            FootageCard(
                                navController,
                                it as MovieResults,
                                Screens.MOVIES_DETAILS_SCREEN
                            )
                        }
                        is SerieResults -> {
                            FootageCard(
                                navController,
                                it as SerieResults,
                                Screens.SERIES_DETAILS_SCREEN
                            )
                        }
                        is MovieEntinty -> {
                            FootageCard(
                                navController,
                                it as MovieEntinty,
                                Screens.MOVIES_DETAILS_SCREEN
                            )
                        }
                        is SerieEntity -> {
                            FootageCard(
                                navController,
                                it as SerieEntity,
                                Screens.SERIES_DETAILS_SCREEN
                            )
                        }
                        is TopRatedMoviesEntity -> {
                            FootageCard(
                                navController,
                                it as TopRatedMoviesEntity,
                                Screens.TOP_RATED_MOVIES_DETAILS_SCREEN
                            )
                        }
                        is TopRatedSeriesEntity -> {
                            FootageCard(
                                navController,
                                it as TopRatedSeriesEntity,
                                Screens.TOP_RATED_SERIES_DETAILS_SCREEN
                            )
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(30.dp))
                }
            }
        } else {
            EmptyData()
        }
    }
}