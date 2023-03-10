package com.dwh.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dwh.domain.models.entities.TrendEntity

@Dao
interface TrendDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(trendEntity: List<TrendEntity>)

    @Query("SELECT * FROM trends")
    suspend fun getAllTrends(): List<TrendEntity>
}