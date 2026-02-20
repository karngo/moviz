package com.example.moviz.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.moviz.data.db.BookmarkedMovieEntity
import com.example.moviz.data.db.MovieEntity
import com.example.moviz.data.db.MovizDatabase
import com.example.moviz.data.mediator.NowPlayingRemoteMediator
import com.example.moviz.data.model.MovieCompact
import com.example.moviz.data.pagingsource.SearchPagingSource
import com.example.moviz.data.pagingsource.TrendingPagingSource
import com.example.moviz.ui.model.MovieDetail
import com.example.moviz.utils.getYearFromDate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.math.round

@OptIn(ExperimentalPagingApi::class)
class ApiRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val database: MovizDatabase
) : ApiRepository {

    fun toMovieDetail(movieData: MovieCompact): MovieDetail {
        return MovieDetail(
            id = movieData.id ?: 0,
            title = movieData.title ?: "",
            desc = movieData.overview ?: "",
            rating = movieData.voteAverage?.let { round(it * 10) / 10 },
            releaseYear = movieData.releaseDate?.let { getYearFromDate(it) } ?: "",
            posterUrl = movieData.posterPath?.let { "https://image.tmdb.org/t/p/w500$it" } ?: "",
            backdropUrl = movieData.backdropPath?.let { "https://image.tmdb.org/t/p/w500$it" } ?: ""
        )
    }

    fun toMovieDetail(movieEntity: MovieEntity): MovieDetail {
        return MovieDetail(
            id = movieEntity.id,
            title = movieEntity.title,
            desc = movieEntity.overview,
            rating = movieEntity.voteAverage?.let { round(it * 10) / 10 },
            releaseYear = getYearFromDate(movieEntity.releaseDate),
            posterUrl = movieEntity.posterPath.let { "https://image.tmdb.org/t/p/w500$it" },
            backdropUrl = movieEntity.backdropPath.let { "https://image.tmdb.org/t/p/w500$it" }
        )
    }

    override fun getNowPlaying(): Flow<PagingData<MovieDetail>> =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            remoteMediator = NowPlayingRemoteMediator(apiService, database),
            pagingSourceFactory = {
                database.movieDao().getNowPlayingMovies()
            }
        ).flow.map { pagingData -> pagingData.map { toMovieDetail(it) } }.flowOn(Dispatchers.IO)


    override fun getTrending(): Flow<PagingData<MovieDetail>> =
        Pager(
            config = PagingConfig(
                pageSize = 20,
            ),
            pagingSourceFactory = {
                TrendingPagingSource(apiService)
            }
        ).flow.map { pagingData -> pagingData.map { toMovieDetail(it) } }.flowOn(Dispatchers.IO)

    override fun searchMovie(query: String): Flow<PagingData<MovieDetail>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
            ),
            pagingSourceFactory = {
                SearchPagingSource(apiService, query)
            }
        ).flow.map { pagingData -> pagingData.map { toMovieDetail(it) } }.flowOn(Dispatchers.IO)
    }

    override suspend fun getMovieDetail(movieId: Long): Flow<MovieDetail?> = flow {
        val response = apiService.getMovieDetail(movieId)
        response.body()?.let { movieData ->
            emit(
                MovieDetail(
                    id = movieData.id ?: 0,
                    title = movieData.title ?: "",
                    desc = movieData.overview ?: "",
                    rating = movieData.voteAverage,
                    releaseYear = movieData.releaseDate?.let { getYearFromDate(it) } ?: "",
                    posterUrl = movieData.posterPath?.let { "https://image.tmdb.org/t/p/w500$it" }
                        ?: "",
                    backdropUrl = movieData.backdropPath?.let { "https://image.tmdb.org/t/p/w500$it" }
                        ?: ""
                )
            )
        } ?: emit(null)
    }.flowOn(Dispatchers.IO)

    override suspend fun updateBookmark(movieId: Long, isBookmarked: Boolean) {
        if (isBookmarked)
            database.bookmarkedMovieDao().bookmarkMovie(BookmarkedMovieEntity(movieId))
        else
            database.bookmarkedMovieDao().removeBookmark(movieId)
    }

    override suspend fun isMovieBookmarked(movieId: Long): Boolean {
        return database.bookmarkedMovieDao().isMovieBookmarked(movieId)
    }

    override suspend fun getBookmarkedMovies(): List<MovieDetail> {
        val bookmarkedEntities = database.bookmarkedMovieDao().getBookmarkedMoviesAvailableLocally()
        return bookmarkedEntities.map { toMovieDetail(it) }
    }
}