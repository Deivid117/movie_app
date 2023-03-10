package com.dwh.movieapp.repository

import com.dwh.domain.models.get.trends.Trends
import kotlinx.coroutines.flow.Flow
import mx.com.satoritech.web.APIService
import mx.com.satoritech.web.BaseApiResponse
import mx.com.satoritech.web.NetworkResult
import javax.inject.Inject

class TrendsRepositoryImp@Inject constructor(
    private val apiService: APIService
): TrendsRepository, BaseApiResponse() {
    override fun getTrends(mediaType: String, timeWindow: String): Flow<NetworkResult<Trends>> = safeApiCall {
        apiService.getTrending(mediaType, timeWindow)
    }
}