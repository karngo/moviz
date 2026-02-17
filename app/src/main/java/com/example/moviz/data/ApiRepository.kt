package com.example.moviz.data

import com.example.moviz.ui.model.MovieDetail

interface ApiRepository {
    suspend fun getNowPlaying(): List<MovieDetail>

    suspend fun getTrending(): List<MovieDetail>
}