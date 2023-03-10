package com.dwh.domain.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dwh.domain.models.get.movies.popular.upcoming.MovieResults
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class MovieEntinty(
    @PrimaryKey
    @SerializedName("id"                ) var id               : Int?           = null,
    @SerializedName("title"             ) var title            : String?        = null,
    @SerializedName("backdrop_path"     ) var backdropPath     : String?        = null,
    @SerializedName("overview"          ) var overview         : String?        = null,
    @SerializedName("vote_average"      ) var voteAverage      : Double?        = null,
    @SerializedName("release_date"      ) var releaseDate      : String?        = null,
)

fun MovieResults.toDatabase() = MovieEntinty(id, title, backdropPath, overview, voteAverage, releaseDate)