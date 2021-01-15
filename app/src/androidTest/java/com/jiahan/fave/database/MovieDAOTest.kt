package com.jiahan.fave.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Before
import com.google.common.truth.Truth.assertThat
import com.jiahan.fave.getOrAwaitValue
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat

@ExperimentalCoroutinesApi
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class MovieDAOTest {

    private lateinit var database: AppDatabase
    private lateinit var dao: MovieDAO

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), AppDatabase::class.java)
            .allowMainThreadQueries().build()
        dao = database.movieDAO()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertMovies_observe_returnCorrectSize() = runBlockingTest {
        // Given - Inserting movies in room
        val movieItem = arrayOf(
            Movie( 1,1, "poster_path_1", "title_1", 3.33f ),
            Movie( 3,3, "poster_path_3", "title_3", 3.33f ),
            Movie( 2,2, "poster_path_2", "title_2", 3.33f )
        )
        dao.insertMovies( *movieItem )

        // When - Observing live data
        val movieList = dao.getMovies().getOrAwaitValue()

        // Then - Returning correct size
        assertThat( movieList.size, `is`(3) )
    }

    @Test
    fun insertMovies_observe_containMovie() = runBlockingTest {
        // Given - Inserting movies in room
        val movieItem = arrayOf(
            Movie( 1,1, "poster_path_1", "title_1", 3.33f ),
            Movie( 3,3, "poster_path_3", "title_3", 3.33f ),
            Movie( 2,2, "poster_path_2", "title_2", 3.33f )
        )
        dao.insertMovies( *movieItem )

        // When - Observing live data
        val movieList = dao.getMovies().getOrAwaitValue()

        // Then - Containing expected movie
        assertThat(movieList).contains(movieItem[1])
    }

    @Test
    fun deleteMovies_observe_returnEmptySize() = runBlockingTest {
        // Given - Inserting and deleting movies in room
        val movieItem = arrayOf(
            Movie( 1,1, "poster_path_1", "title_1", 3.33f ),
            Movie( 3,3, "poster_path_3", "title_3", 3.33f ),
            Movie( 2,2, "poster_path_2", "title_2", 3.33f )
        )
        dao.insertMovies( *movieItem )
        dao.deleteMovies()

        // When - Observing live data
        val movieList = dao.getMovies().getOrAwaitValue()

        // Then - Returning empty size
        assertThat( movieList.size, `is`(0) )
    }



}