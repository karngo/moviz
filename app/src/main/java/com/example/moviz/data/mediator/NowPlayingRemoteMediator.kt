package com.example.moviz.data.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.moviz.data.ApiService
import com.example.moviz.data.db.MovieEntity
import com.example.moviz.data.db.MovizDatabase
import com.example.moviz.data.db.RemoteKey
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class NowPlayingRemoteMediator(
    private val apiService: ApiService,
    private val database: MovizDatabase
) : RemoteMediator<Int, MovieEntity>() {

    companion object {
        private const val NOW_PLAYING_KEY = 1L
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val remoteKey = database.remoteKeyDao().remoteKeyByKey(NOW_PLAYING_KEY)
                    remoteKey?.nextKey
                        ?: return MediatorResult.Success(endOfPaginationReached = true)
                }
            }

            val response = apiService.getNowPlaying(loadKey).body()
            val movies = response?.results ?: emptyList()
            val endOfPaginationReached = movies.isEmpty()

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.remoteKeyDao().clearRemoteKeys()
                    database.movieDao().clearAllMovies()
                }

                val remoteKey = RemoteKey(
                    movieId = NOW_PLAYING_KEY,
                    prevKey = if (loadKey > 1) loadKey - 1 else null,
                    nextKey = if (endOfPaginationReached) null else loadKey + 1
                )

                database.remoteKeyDao().insertKey(remoteKey)

                val movieEntities = movies.map { movie ->
                    MovieEntity(
                        id = movie.id ?: 0,
                        title = movie.title ?: "",
                        overview = movie.overview ?: "",
                        voteAverage = movie.voteAverage,
                        releaseDate = movie.releaseDate ?: "",
                        posterPath = movie.posterPath ?: "",
                        backdropPath = movie.backdropPath ?: "",
                        adult = movie.adult,
                        originalLanguage = movie.originalLanguage,
                        popularity = movie.popularity,
                        voteCount = movie.voteCount
                    )
                }

                database.movieDao().insertMovies(movieEntities)
            }

            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: IOException) {
            if (database.movieDao().getMovieCount() > 0) {
                MediatorResult.Success(endOfPaginationReached = true)
            } else {
                MediatorResult.Error(e)
            }
        } catch (e: HttpException) {
            if (database.movieDao().getMovieCount() > 0) {
                MediatorResult.Success(endOfPaginationReached = true)
            } else {
                MediatorResult.Error(e)
            }
        }
    }
}

