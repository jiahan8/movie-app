package com.jiahan.fave.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jiahan.fave.database.Movie
import com.jiahan.fave.repository.Repository
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(private val moviesRepository: Repository) : ViewModel() {

    /**
     * Live data that main fragment observe
     */
    val movielist : LiveData<List<Movie>> = moviesRepository.getMovies()

    /**
     * Prevent calling when configuration changes
     */
    init {
        getMovie(1)
    }

    /**
     * Get movie list based on page number.
     */
    fun getMovie(page: Int) {
        viewModelScope.launch{
            moviesRepository.getMovieList(page)
        }
    }

}