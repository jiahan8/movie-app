package com.jiahan.fave.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jiahan.fave.domain.MovieDetail
import com.jiahan.fave.repository.Repository
import kotlinx.coroutines.launch

class DetailViewModel @ViewModelInject constructor(private val moviesRepository: Repository) : ViewModel() {

    /**
     * Live data that detail fragment observe
     */
    private val _movieDetail = MutableLiveData<MovieDetail>()
    val movieDetail: LiveData<MovieDetail>
        get() = _movieDetail

    /**
     * Get movie detail based on movie id, then update live data.
     */
    fun getMovieDetail(movieId: Int) {
        try {
            viewModelScope.launch{
                val movieDetail = moviesRepository.getMovieDetail(movieId)
                _movieDetail.postValue(movieDetail)
            }
        } catch (e: Exception) {
        }
    }

}
