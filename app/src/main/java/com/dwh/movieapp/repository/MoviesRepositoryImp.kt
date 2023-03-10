package com.dwh.movieapp.repository

import com.dwh.domain.models.get.movies.popular.upcoming.Movies
import com.dwh.domain.models.get.movies.details.MoviesDetails
import kotlinx.coroutines.flow.Flow
import mx.com.satoritech.web.APIService
import mx.com.satoritech.web.BaseApiResponse
import mx.com.satoritech.web.NetworkResult
import javax.inject.Inject

class MoviesRepositoryImp@Inject constructor(
    private val apiService: APIService
): MoviesRepository, BaseApiResponse() {

    override fun getPopularMovies() = safeApiCall {
        apiService.getPopularMovies()
    }

    override fun getUpcomingMovies(): Flow<NetworkResult<Movies>> = safeApiCall {
        apiService.getUpcomingMovies()
    }

    override fun getMovieDetails(movieId: Long): Flow<NetworkResult<MoviesDetails>> = safeApiCall {
        apiService.getMovieDetails(movieId)
    }

    override fun getTopRatedMovies(): Flow<NetworkResult<Movies>> = safeApiCall {
        apiService.getTopRatedMovies()
    }

}