package com.example.moviz.data

import androidx.paging.PagingData
import com.example.moviz.ui.model.MovieDetail
import kotlinx.coroutines.flow.Flow

interface ApiRepository {
    fun getNowPlaying(): Flow<PagingData<MovieDetail>>

    fun getTrending(): Flow<PagingData<MovieDetail>>

    fun searchMovie(query: String): Flow<PagingData<MovieDetail>>

    suspend fun getMovieDetail(movieId: Long): Flow<MovieDetail?>

    suspend fun updateBookmark(movieId: Long, isBookmarked: Boolean)

    suspend fun isMovieBookmarked(movieId: Long): Boolean

    suspend fun getBookmarkedMovies(): List<MovieDetail>
}