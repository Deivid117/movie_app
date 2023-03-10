package com.dwh.movieapp.repository

import com.dwh.domain.models.entities.MovieEntinty
import com.dwh.domain.models.entities.TrendEntity

interface LocalTrendRepository {
    suspend fun insert(trendEntity: List<TrendEntity>)
    suspend fun getAllTrends(): List<TrendEntity>
}