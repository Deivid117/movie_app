package com.dwh.movieapp.repository

import com.dwh.domain.models.get.series.details.SeriesDetails
import com.dwh.domain.models.get.series.popular.upcoming.Series
import kotlinx.coroutines.flow.Flow
import mx.com.satoritech.web.APIService
import mx.com.satoritech.web.BaseApiResponse
import mx.com.satoritech.web.NetworkResult
import javax.inject.Inject

class SeriesRepositoryImp@Inject constructor(
    private val apiService: APIService
): SeriesRepository, BaseApiResponse() {
    override fun getPopularSeries(): Flow<NetworkResult<Series>> = safeApiCall {
        apiService.getPopularSeries()
    }

    override fun getSerieDetails(tvId: Long): Flow<NetworkResult<SeriesDetails>> = safeApiCall {
        apiService.getSerieDetails(tvId)
    }

    override fun getTopRatedSeries(): Flow<NetworkResult<Series>> = safeApiCall {
        apiService.getTopRatedSeries()
    }
}