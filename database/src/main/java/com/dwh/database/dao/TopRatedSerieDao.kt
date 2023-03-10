package com.dwh.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dwh.domain.models.entities.TopRatedSeriesEntity

@Dao
interface TopRatedSerieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopRatedSeriesEntity(topRatedSeriesEntity: List<TopRatedSeriesEntity>)

    @Query("SELECT * FROM top_rated_series")
    suspend fun getAllTopRatedSeriesEntity(): List<TopRatedSeriesEntity>

    @Query("SELECT * FROM top_rated_series WHERE id = :idSerie LIMIT 1")
    suspend fun findTopRatedSeriesEntityById(idSerie: Int): TopRatedSeriesEntity
}