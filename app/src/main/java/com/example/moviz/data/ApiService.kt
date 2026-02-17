package com.example.moviz.data

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("movie/now_playing?language=en-US&page=1")
    suspend fun getNowPlaying(): Response<MovieData>
}