package com.dwh.movieapp.repository

import com.dwh.domain.models.get.series.details.SeriesDetails
import com.dwh.domain.models.get.series.popular.upcoming.Series
import kotlinx.coroutines.flow.Flow
import mx.com.satoritech.web.NetworkResult

interface SeriesRepository {
    fun getPopularSeries(): Flow<NetworkResult<Series>>
    fun getSerieDetails(tvId: Long): Flow<NetworkResult<SeriesDetails>>
    fun getTopRatedSeries(): Flow<NetworkResult<Series>>
}