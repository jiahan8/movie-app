package com.jiahan.fave.repository

import androidx.lifecycle.LiveData
import com.jiahan.fave.database.DatabaseMovie
import com.jiahan.fave.domain.MovieDetail

class FakeMoviesRepository : Repository{

    lateinit var livedata: LiveData<List<DatabaseMovie>>
    lateinit var moviedetail: MovieDetail

    override fun getMovies(): LiveData<List<DatabaseMovie>> {
        return livedata
    }

    override suspend fun getMovieList(page: Int) {

    }

    override suspend fun getMovieDetail(movieId: Int): MovieDetail? {
        return moviedetail
    }


}