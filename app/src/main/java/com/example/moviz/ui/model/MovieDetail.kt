package com.example.moviz.ui.model

data class MovieDetail(
    val id: Long,
    val title: String = "",
    val desc: String = "",
    val rating: Double? = null,
    val releaseYear: String = "",
    val posterUrl: String = "",
    val backdropUrl: String = ""
)
