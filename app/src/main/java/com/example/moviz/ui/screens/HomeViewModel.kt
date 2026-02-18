package com.example.moviz.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviz.data.ApiRepository
import com.example.moviz.ui.model.MovieDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val apiRepository: ApiRepository
) : ViewModel() {
    private val _nowPlaying = MutableStateFlow<List<MovieDetail>>(emptyList())
    val nowPlaying: StateFlow<List<MovieDetail>> = _nowPlaying.asStateFlow()

    private val _trending = MutableStateFlow<List<MovieDetail>>(emptyList())
    val trending: StateFlow<List<MovieDetail>> = _trending.asStateFlow()

    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            _nowPlaying.value = apiRepository.getNowPlaying()
            _trending.value = apiRepository.getTrending()
        }
    }
}

