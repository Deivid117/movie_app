package com.dwh.movieapp.repository

import com.dwh.domain.models.get.trends.Trends
import kotlinx.coroutines.flow.Flow
import mx.com.satoritech.web.NetworkResult

interface TrendsRepository {
    fun getTrends(mediaType: String, timeWindow: String): Flow<NetworkResult<Trends>>
}