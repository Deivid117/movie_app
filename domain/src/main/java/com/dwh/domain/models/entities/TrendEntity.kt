package com.dwh.domain.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dwh.domain.models.get.series.popular.upcoming.SerieResults
import com.dwh.domain.models.get.trends.TrendsResults
import com.google.gson.annotations.SerializedName

@Entity(tableName = "trends")
data class TrendEntity(
    @PrimaryKey
    @SerializedName("id"                ) var id               : Int?           = null,
    @SerializedName("title"             ) var title            : String?        = null,
    @SerializedName("backdrop_path"     ) var backdropPath     : String?        = null,
)

fun TrendsResults.toDatabase() = TrendEntity(id, title, backdropPath)