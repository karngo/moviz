package com.example.moviz.data

import com.example.moviz.data.model.CompleteMovieData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/now_playing?language=en-US")
    suspend fun getNowPlaying(@Query("page") page: Int): Response<MovieData>

    @GET("trending/movie/day?language=en-US")
    suspend fun getTrending(): Response<MovieData>

    @GET("search/movie?include_adult=false&language=en-US")
    suspend fun searchMovie(
        @Query("query") query: String,
        @Query("page") page: Int
    ): Response<MovieData>

    @GET("movie/{movieId}")
    suspend fun getMovieDetail(@Path("movieId") movieId: Long): Response<CompleteMovieData>
}