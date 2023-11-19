package com.ikapurwanti.movie.presentation.feature.detail

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ikapurwanti.movie.data.repository.MovieRepository
import com.ikapurwanti.movie.model.VideoViewParam
import com.ikapurwanti.movie.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(
    private val extras: Bundle?,
    private val repo: MovieRepository
): ViewModel() {

    val movie = extras?.getParcelable<VideoViewParam>(DetailActivity.EXTRA_MOVIE)

}