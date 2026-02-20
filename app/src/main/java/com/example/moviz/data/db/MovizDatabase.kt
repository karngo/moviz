package com.example.moviz.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [MovieEntity::class, RemoteKey::class, BookmarkedMovieEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MovizDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun remoteKeyDao(): RemoteKeyDao
    abstract fun bookmarkedMovieDao(): BookmarkedMovieDao
}
