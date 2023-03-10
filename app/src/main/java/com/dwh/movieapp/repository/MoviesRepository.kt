package com.dwh.movieapp.repository

import com.dwh.domain.models.get.movies.popular.upcoming.Movies
import com.dwh.domain.models.get.movies.details.MoviesDetails
import mx.com.satoritech.web.NetworkResult
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    fun getPopularMovies(): Flow<NetworkResult<Movies>>
    fun getUpcomingMovies(): Flow<NetworkResult<Movies>>
    fun getMovieDetails(movieId: Long): Flow<NetworkResult<MoviesDetails>>
    fun getTopRatedMovies(): Flow<NetworkResult<Movies>>
}