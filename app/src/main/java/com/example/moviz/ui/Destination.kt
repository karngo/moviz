package com.example.moviz.ui

import kotlinx.serialization.Serializable

sealed class Destination {
    @Serializable
    object Main : Destination()

    @Serializable
    data class Detail(val movieId: Long) : Destination()
}

sealed class MainDestination {
    @Serializable
    object Home : MainDestination()

    @Serializable
    object Search : MainDestination()

    @Serializable
    object Bookmarked : MainDestination()
}