package com.dwh.domain.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dwh.domain.models.get.movies.popular.upcoming.MovieResults
import com.dwh.domain.models.get.series.popular.upcoming.SerieResults
import com.google.gson.annotations.SerializedName

@Entity(tableName = "series")
data class SerieEntity(
    @PrimaryKey
    @SerializedName("id"                ) var id               : Int?              = null,
    @SerializedName("name"              ) var name             : String?           = null,
    @SerializedName("backdrop_path"     ) var backdropPath     : String?           = null,
    @SerializedName("overview"          ) var overview         : String?           = null,
    @SerializedName("vote_average"      ) var voteAverage      : Double?           = null,
    @SerializedName("first_air_date"    ) var firstAirDate     : String?           = null,
)

fun SerieResults.toDatabase() = SerieEntity(id, name, backdropPath, overview, voteAverage, firstAirDate)