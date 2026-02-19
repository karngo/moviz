package com.example.moviz.ui.screens.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.moviz.data.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val apiRepository: ApiRepository
) : ViewModel() {
    val nowPlaying = apiRepository.getNowPlaying().cachedIn(viewModelScope)
    val trending = apiRepository.getTrending().cachedIn(viewModelScope)
}

