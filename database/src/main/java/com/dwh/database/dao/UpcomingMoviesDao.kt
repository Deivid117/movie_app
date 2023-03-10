package com.dwh.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dwh.domain.models.entities.UpcomingMoviesEntity

@Dao
interface UpcomingMoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(upcomingMoviesEntity: List<UpcomingMoviesEntity>)

    @Query("SELECT * FROM upcoming_movies")
    suspend fun getAllUpcomingMovies(): List<UpcomingMoviesEntity>
}
