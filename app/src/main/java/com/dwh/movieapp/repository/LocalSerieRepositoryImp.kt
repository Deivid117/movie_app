package com.dwh.movieapp.repository

import com.dwh.domain.models.entities.SerieEntity
import com.dwh.domain.models.entities.TopRatedSeriesEntity
import mx.com.satoritech.database.DBMovieApp
import javax.inject.Inject

class LocalSerieRepositoryImp@Inject constructor(
    private val dbMovieApp: DBMovieApp
): LocalSerieRepository {

    override suspend fun insert(serieEntity: List<SerieEntity>) {
        return dbMovieApp.serieDao().insert(serieEntity)
    }

    override suspend fun getAllSeries(): List<SerieEntity> {
        return dbMovieApp.serieDao().getAllSeries()
    }

    override suspend fun findSerieById(idSerie: Int): SerieEntity {
        return dbMovieApp.serieDao().findSerieById(idSerie)
    }

    override suspend fun insertTopRatedSeriesEntity(topRatedSeriesEntity: List<TopRatedSeriesEntity>) {
        return dbMovieApp.topRatedSeries().insertTopRatedSeriesEntity(topRatedSeriesEntity)
    }

    override suspend fun getAllTopRatedSeriesEntity(): List<TopRatedSeriesEntity> {
        return dbMovieApp.topRatedSeries().getAllTopRatedSeriesEntity()
    }

    override suspend fun findTopRatedSeriesEntityById(idSerie: Int): TopRatedSeriesEntity {
        return dbMovieApp.topRatedSeries().findTopRatedSeriesEntityById(idSerie)
    }
}