package com.example.moviz.ui.screens.moviedetail

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
class MovieDetailViewModel @Inject constructor(private val apiRepository: ApiRepository) :
    ViewModel() {
    private var _movieDetail = MutableStateFlow(MovieDetail(id = 0))
    val movieDetail = _movieDetail.asStateFlow()

    private var _isBookmarked = MutableStateFlow(false)
    val isBookmarked = _isBookmarked.asStateFlow()

    fun fetchMovieDetails(movieId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            apiRepository.getMovieDetail(movieId).collect {
                if (it != null) {
                    _movieDetail.value = it
                    _isBookmarked.value = apiRepository.isMovieBookmarked(movieId)
                }
            }
        }
    }

    fun updateBookmark(movieId: Long, isBookmarked: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            apiRepository.updateBookmark(movieId, isBookmarked)
            _isBookmarked.value = isBookmarked
        }
    }
}