package com.dwh.domain.models

data class MenuItem(
    val title: String,
    val icon: Int,
    val item1: String? = null,
    val item2: String? = null,
    val route1: String,
    val route2: String? = null,
)
