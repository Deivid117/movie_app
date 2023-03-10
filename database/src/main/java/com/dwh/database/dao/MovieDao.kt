package com.dwh.database.dao

import androidx.room.*
import com.dwh.domain.models.entities.MovieEntinty

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movieEntinty: List<MovieEntinty>)

    @Query("SELECT * FROM movies")
    suspend fun getAllMovies(): List<MovieEntinty>

    @Query("SELECT * FROM movies WHERE id = :idMovie LIMIT 1")
    suspend fun findMovieById(idMovie: Int):MovieEntinty
}