package com.example.moviz.di

import android.content.Context
import androidx.room.Room
import com.example.moviz.data.db.MovizDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideMovizDatabase(
        @ApplicationContext context: Context
    ): MovizDatabase {
        return Room.databaseBuilder(
            context,
            MovizDatabase::class.java,
            "moviz_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideMovieDao(database: MovizDatabase) = database.movieDao()

    @Singleton
    @Provides
    fun provideRemoteKeyDao(database: MovizDatabase) = database.remoteKeyDao()
}

