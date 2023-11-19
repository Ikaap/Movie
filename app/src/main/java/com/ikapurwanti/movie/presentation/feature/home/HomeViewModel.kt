package com.ikapurwanti.movie.presentation.feature.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ikapurwanti.movie.data.repository.MovieRepository
import com.ikapurwanti.movie.model.VideoViewParam
import com.ikapurwanti.movie.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repo : MovieRepository
): ViewModel() {

    private val _movie = MutableLiveData<ResultWrapper<List<VideoViewParam>>>()
    val movie: LiveData<ResultWrapper<List<VideoViewParam>>>
        get() = _movie

    fun getMovie() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getMovies().collect {
                _movie.postValue(it)
            }
        }
    }
}