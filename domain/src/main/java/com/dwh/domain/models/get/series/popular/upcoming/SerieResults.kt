package com.dwh.domain.models.get.series.popular.upcoming

import com.google.gson.annotations.SerializedName

data class SerieResults(
    @SerializedName("poster_path"       ) var posterPath       : String?           = null,
    @SerializedName("popularity"        ) var popularity       : Double?           = null,
    @SerializedName("id"                ) var id               : Int?              = null,
    @SerializedName("backdrop_path"     ) var backdropPath     : String?           = null,
    @SerializedName("vote_average"      ) var voteAverage      : Double?           = null,
    @SerializedName("overview"          ) var overview         : String?           = null,
    @SerializedName("first_air_date"    ) var firstAirDate     : String?           = null,
    @SerializedName("origin_country"    ) var originCountry    : ArrayList<String> = arrayListOf(),
    @SerializedName("genre_ids"         ) var genreIds         : ArrayList<Int>    = arrayListOf(),
    @SerializedName("original_language" ) var originalLanguage : String?           = null,
    @SerializedName("vote_count"        ) var voteCount        : Int?              = null,
    @SerializedName("name"              ) var name             : String?           = null,
    @SerializedName("original_name"     ) var originalName     : String?           = null
)
