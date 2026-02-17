package com.example.moviz.data

import com.example.moviz.ui.model.MovieDetail
import com.example.moviz.utils.toEpoch
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(private val apiService: ApiService) : ApiRepository {

    fun toMovieDetail(movieData: MovieCompact): MovieDetail {
        return MovieDetail(
            id = movieData.id ?: 0,
            title = movieData.title ?: "",
            desc = movieData.overview ?: "",
            rating = movieData.voteAverage,
            releaseData = movieData.releaseDate?.toEpoch(),
            posterUrl = movieData.posterPath?.let { "https://image.tmdb.org/t/p/w500$it" } ?: "",
            backdropUrl = movieData.backdropPath?.let { "https://image.tmdb.org/t/p/w500$it" } ?: ""
        )
    }

    override suspend fun getNowPlaying(): List<MovieDetail> =
        apiService.getNowPlaying().body()?.results?.map { toMovieDetail(it) } ?: emptyList()

    override suspend fun getTrending(): List<MovieDetail> =
        apiService.getTrending().body()?.results?.map { toMovieDetail(it) } ?: emptyList()
}