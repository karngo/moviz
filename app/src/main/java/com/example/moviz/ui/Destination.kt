package com.example.moviz.ui

import kotlinx.serialization.Serializable

sealed class Destination {
    @Serializable
    object Home : Destination()

    @Serializable
    object MovieDetail : Destination()
}