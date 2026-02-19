package com.example.moviz.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.moviz.ui.model.MovieDetail
import com.example.moviz.utils.getYearFromDate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(private val apiService: ApiService) : ApiRepository {

    fun toMovieDetail(movieData: MovieCompact): MovieDetail {
        return MovieDetail(
            id = movieData.id ?: 0,
            title = movieData.title ?: "",
            desc = movieData.overview ?: "",
            rating = movieData.voteAverage,
            releaseYear = movieData.releaseDate?.let { getYearFromDate(it) } ?: "",
            posterUrl = movieData.posterPath?.let { "https://image.tmdb.org/t/p/w500$it" } ?: "",
            backdropUrl = movieData.backdropPath?.let { "https://image.tmdb.org/t/p/w500$it" } ?: ""
        )
    }

    override fun getNowPlaying(): Flow<PagingData<MovieDetail>> =
        Pager(
            config = PagingConfig(
                pageSize = 20,
            ),
            pagingSourceFactory = {
                NowPlayingPagingSource(apiService)
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
        if (query.trim().isEmpty()) {
            return flowOf(PagingData.empty())
        }

        return Pager(
            config = PagingConfig(
                pageSize = 20,
            ),
            pagingSourceFactory = {
                SearchPagingSource(apiService, query)
            }
        ).flow.map { pagingData -> pagingData.map { toMovieDetail(it) } }.flowOn(Dispatchers.IO)
    }
}