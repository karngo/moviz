package com.example.moviz.ui.screens.main.bookmarked

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviz.data.ApiRepository
import com.example.moviz.ui.model.MovieDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkedViewModel @Inject constructor(private val apiRepository: ApiRepository) :
    ViewModel() {
    private var _bookmarkedMovies = MutableStateFlow<List<MovieDetail>>(emptyList())
    val bookmarkedMovies = _bookmarkedMovies.asStateFlow()

    init {
        fetchBookmarkedMovies()
    }

    fun fetchBookmarkedMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val movies = apiRepository.getBookmarkedMovies()
                _bookmarkedMovies.value = movies
            } finally {

            }
        }
    }

    fun removeBookmark(movieId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            apiRepository.updateBookmark(movieId, false)
            fetchBookmarkedMovies()
        }
    }
}

