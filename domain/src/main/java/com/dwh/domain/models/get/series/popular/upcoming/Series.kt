package com.dwh.domain.models.get.series.popular.upcoming

import com.google.gson.annotations.SerializedName

data class Series(
    @SerializedName("page"          ) var page         : Int?               = null,
    @SerializedName("results"       ) var results      : ArrayList<SerieResults> = arrayListOf(),
    @SerializedName("total_results" ) var totalResults : Int?               = null,
    @SerializedName("total_pages"   ) var totalPages   : Int?               = null
)
