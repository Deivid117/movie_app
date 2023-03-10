package com.dwh.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dwh.domain.models.entities.MovieEntinty
import com.dwh.domain.models.entities.SerieEntity

@Dao
interface SerieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(serieEntity: List<SerieEntity>)

    @Query("SELECT * FROM series")
    suspend fun getAllSeries(): List<SerieEntity>

    @Query("SELECT * FROM series WHERE id = :idSerie LIMIT 1")
    suspend fun findSerieById(idSerie: Int): SerieEntity
}