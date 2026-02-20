package com.example.moviz.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BookmarkedMovieDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun bookmarkMovie(movie: BookmarkedMovieEntity)

    @Query("DELETE FROM bookmarked_movies WHERE movieId = :movieId")
    suspend fun removeBookmark(movieId: Long)

    @Query("SELECT EXISTS(SELECT 1 FROM bookmarked_movies WHERE movieId = :movieId)")
    suspend fun isMovieBookmarked(movieId: Long): Boolean

    @Query(
        """
        SELECT m.* FROM movies m 
        INNER JOIN bookmarked_movies b ON m.id = b.movieId 
        ORDER BY m.id ASC
        """
    )
    suspend fun getBookmarkedMoviesAvailableLocally(): List<MovieEntity>

    @Query("DELETE FROM bookmarked_movies")
    suspend fun clearAllBookmarks()
}

