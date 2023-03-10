package com.dwh.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dwh.domain.models.entities.TopRatedMoviesEntity

@Dao
interface TopRatedMovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopRatedMovies(topRatedMoviesEntity: List<TopRatedMoviesEntity>)

    @Query("SELECT * FROM top_rated_movies")
    suspend fun getAllTopRatedMovies(): List<TopRatedMoviesEntity>

    @Query("SELECT * FROM top_rated_movies WHERE id = :idMovie LIMIT 1")
    suspend fun findTopRatedMovieById(idMovie: Int): TopRatedMoviesEntity
}