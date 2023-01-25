package com.jiahan.fave.repository

import androidx.lifecycle.LiveData
import com.jiahan.fave.database.Movie
import com.jiahan.fave.domain.MovieDetail

/**
 * Repository interface for MoviesRepository and FakeMoviesRepository (test)
 */
interface Repository {

    fun getMovies(): LiveData<List<Movie>>

    suspend fun getMovieList(page: Int)

    suspend fun getMovieDetail(movieId: Int): MovieDetail?

}