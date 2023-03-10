package com.dwh.movieapp.repository

import com.dwh.domain.models.entities.TrendEntity
import mx.com.satoritech.database.DBMovieApp
import javax.inject.Inject

class LocalTrendRepositoryImp@Inject constructor(
    private val dbMovieApp: DBMovieApp
): LocalTrendRepository {
    override suspend fun insert(trendEntity: List<TrendEntity>) {
        return dbMovieApp.trendDao().insert(trendEntity)
    }

    override suspend fun getAllTrends(): List<TrendEntity> {
        return dbMovieApp.trendDao().getAllTrends()
    }
}