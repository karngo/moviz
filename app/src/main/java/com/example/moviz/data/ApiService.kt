package com.example.moviz.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/now_playing?language=en-US&page=1")
    suspend fun getNowPlaying(): Response<MovieData>

    @GET("trending/movie/day?language=en-US")
    suspend fun getTrending(): Response<MovieData>

    @GET("search/movie?include_adult=false&language=en-US&page=1")
    suspend fun searchMovie(@Query("query") query: String): Response<MovieData>
}