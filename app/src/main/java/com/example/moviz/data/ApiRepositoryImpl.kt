package com.example.moviz.data

import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(private val apiService: ApiService) : ApiRepository {
    override suspend fun getNowPlaying(): List<MovieCompact> = apiService.getNowPlaying().body()?.results ?: emptyList()
}