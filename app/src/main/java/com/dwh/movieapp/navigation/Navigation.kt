package com.dwh.movieapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dwh.movieapp.ui.home.HomeScreen
import com.dwh.movieapp.ui.movies.popular.movies.PopularMoviesDetailsScreen
import com.dwh.movieapp.ui.movies.popular.movies.PopularMoviesScreen
import com.dwh.movieapp.ui.movies.top.rated.movies.TopRatedMoviesDetailsScreen
import com.dwh.movieapp.ui.movies.top.rated.movies.TopRatedSeriesScreen
import com.dwh.movieapp.ui.series.popular.series.PopularSeriesDetailsScreen
import com.dwh.movieapp.ui.series.popular.series.PopularSeriesScreen
import com.dwh.movieapp.ui.series.top.rated.series.TopRatedMoviesScreen
import com.dwh.movieapp.ui.series.top.rated.series.TopRatedSeriesDetailsScreen

@Composable
fun Navigation(navController: NavController) {
    NavHost(
        navController = navController as NavHostController,
        startDestination = Screens.HOME_SCREEN
    ){
        //HOME
        composable(route = Screens.HOME_SCREEN) {
            HomeScreen(navController)
        }

        //MOVIES
        composable(route = Screens.POPULAR_MOVIES_SCREEN) {
            PopularMoviesScreen(navController)
        }
        composable(route = Screens.TOP_RATED_MOVIES_SCREEN) {
            TopRatedMoviesScreen(navController)
        }
        composable(route = Screens.MOVIES_DETAILS_SCREEN + "/{movieId}") {backStackEntry ->
            backStackEntry.arguments?.getString("movieId")?.let {
                PopularMoviesDetailsScreen(navController, it)
            }
        }
        composable(route = Screens.TOP_RATED_MOVIES_DETAILS_SCREEN + "/{movieId}") {backStackEntry ->
            backStackEntry.arguments?.getString("movieId")?.let {
                TopRatedMoviesDetailsScreen(navController, it)
            }
        }

        //SERIES
        composable(route = Screens.POPULAR_SERIES_SCREEN) {
            PopularSeriesScreen(navController)
        }
        composable(route = Screens.TOP_RATED_SERIES_SCREEN) {
            TopRatedSeriesScreen(navController)
        }
        composable(route = Screens.SERIES_DETAILS_SCREEN + "/{tvId}") {backStackEntry ->
            backStackEntry.arguments?.getString("tvId")?.let {
                PopularSeriesDetailsScreen(navController, it)
            }
        }
        composable(route = Screens.TOP_RATED_SERIES_DETAILS_SCREEN + "/{tvId}") {backStackEntry ->
            backStackEntry.arguments?.getString("tvId")?.let {
                TopRatedSeriesDetailsScreen(navController, it)
            }
        }
    }
}