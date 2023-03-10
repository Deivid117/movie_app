package com.dwh.domain.models.get.movies.popular.upcoming

import com.google.gson.annotations.SerializedName

data class Movies(
    @SerializedName("page"          ) var page         : Int?               = null,
    @SerializedName("results"       ) var results      : ArrayList<MovieResults> = arrayListOf(),
    @SerializedName("total_results" ) var totalResults : Int?               = null,
    @SerializedName("total_pages"   ) var totalPages   : Int?               = null
)
