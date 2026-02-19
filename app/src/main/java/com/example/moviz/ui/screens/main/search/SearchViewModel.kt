package com.example.moviz.ui.screens.main.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviz.data.ApiRepository
import com.example.moviz.ui.model.MovieDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val apiRepository: ApiRepository) : ViewModel() {
    private val _searchResults = MutableStateFlow<List<MovieDetail>>(emptyList())
    val searchResults: StateFlow<List<MovieDetail>> = _searchResults

    fun searchMovies(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val results = apiRepository.searchMovie(query)
            _searchResults.value = results
        }
    }
}

