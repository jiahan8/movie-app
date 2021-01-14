package com.jiahan.fave.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.jiahan.fave.BuildConfig
import com.jiahan.fave.database.DatabaseMovie
import com.jiahan.fave.database.MovieDAO
import com.jiahan.fave.domain.MovieDetail
import com.jiahan.fave.network.NetworkService
import com.jiahan.fave.network.asDatabaseModel
import com.jiahan.fave.network.asDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepository @Inject constructor(
    private val movieDAO: MovieDAO,
    private val networkService: NetworkService) : Repository {

    /**
     * Movies that will be shown on the screen.
     */
    override fun getMovies() : LiveData<List<DatabaseMovie>> = movieDAO.getMovies()

    /**
     * Get Movies from remote database, then syncing to Room.
     * If page is 1, user is refreshing the page, so remove existing movies in Room and only store new movies,
     * else add loaded movies.
     */
    override suspend fun getMovieList(page: Int) {
        try {
            val movielist = networkService.getMovieList(
                    BuildConfig.API_KEY,
                    "2021-01-01",
                    "2010-01-01",
                    "release_date.desc",
                    page,
                )
            if( page == 1 ) {
                movieDAO.deleteMovies()
                movieDAO.deleteSequence()
            }
            movieDAO.insertMovies(*movielist.asDatabaseModel())
        } catch (e: Exception) {
        }
    }

    /**
     * Get movie detail from remote database based on movie id.
     */
    override suspend fun getMovieDetail(movieId: Int) : MovieDetail?{
        var movieDetail : MovieDetail? = null
        try {
             movieDetail = networkService.getMovieDetail(
                movieId,
                BuildConfig.API_KEY,
            ).asDomainModel()
        } catch (e: Exception) {
        }
        return movieDetail
    }

}