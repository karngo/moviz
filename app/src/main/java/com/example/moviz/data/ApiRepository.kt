package com.example.moviz.data

interface ApiRepository {
    suspend fun getNowPlaying(): List<MovieCompact>
}