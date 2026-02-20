package com.example.moviz.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "bookmarked_movies"
)
data class BookmarkedMovieEntity(
    @PrimaryKey
    val movieId: Long
)

