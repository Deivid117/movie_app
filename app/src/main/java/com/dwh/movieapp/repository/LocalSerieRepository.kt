package com.dwh.movieapp.repository

import com.dwh.domain.models.entities.SerieEntity
import com.dwh.domain.models.entities.TopRatedSeriesEntity

interface LocalSerieRepository {
    //Get popular series
    suspend fun insert(serieEntity: List<SerieEntity>)
    suspend fun getAllSeries(): List<SerieEntity>
    //Get popular serie details
    suspend fun findSerieById(idSerie: Int): SerieEntity
    //Get top rated series
    suspend fun insertTopRatedSeriesEntity(topRatedSeriesEntity: List<TopRatedSeriesEntity>)
    suspend fun getAllTopRatedSeriesEntity(): List<TopRatedSeriesEntity>
    //Get top rated serie details
    suspend fun findTopRatedSeriesEntityById(idSerie: Int): TopRatedSeriesEntity
}