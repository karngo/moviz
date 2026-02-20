package com.example.moviz.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey
    val id: Long,
    val title: String,
    val overview: String,
    val voteAverage: Double?,
    val releaseDate: String,
    val posterPath: String,
    val backdropPath: String,
    val adult: Boolean?,
    val originalLanguage: String?,
    val popularity: Double?,
    val voteCount: Long?
)

