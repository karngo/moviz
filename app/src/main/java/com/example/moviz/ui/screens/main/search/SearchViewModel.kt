package com.example.moviz.ui.screens.main.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.moviz.data.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val apiRepository: ApiRepository) : ViewModel() {
    private val _query = MutableStateFlow("")

    @OptIn(ExperimentalCoroutinesApi::class)
    val searchResults = _query
        .flatMapLatest { apiRepository.searchMovie(it).cachedIn(viewModelScope) }

    fun searchMovies(query: String) {
        _query.value = query
    }
}
