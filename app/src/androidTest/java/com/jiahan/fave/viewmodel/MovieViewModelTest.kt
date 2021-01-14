package com.jiahan.fave.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.jiahan.fave.database.AppDatabase
import com.jiahan.fave.getOrAwaitValue
import com.jiahan.fave.repository.FakeMoviesRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class MovieViewModelTest {

    private lateinit var movieViewModel: MainViewModel
    private lateinit var appDatabase: AppDatabase
    private var hiltRule = HiltAndroidRule(this)
    private val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val rule = RuleChain.outerRule(hiltRule).around(instantTaskExecutorRule)

    @Inject
    lateinit var repository: FakeMoviesRepository

    @Before
    fun setupViewModel() {
        hiltRule.inject()

        val context = InstrumentationRegistry.getInstrumentation().targetContext
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()

        movieViewModel = MainViewModel( repository )
    }

    @After
    fun tearDown() {
        appDatabase.close()
    }

    @Test
    fun addNewMovies_returnCorrectSize() {
        movieViewModel.getMovie(1)

        val value = movieViewModel.movielist.getOrAwaitValue()

        assertThat( value.size, `is` (20) )
    }

    @Test
    fun addNewMovies_returnCorrectSize2() {
        movieViewModel.getMovie(1)
        movieViewModel.getMovie(2)
        movieViewModel.getMovie(3)

        val value = movieViewModel.movielist.getOrAwaitValue()

        assertThat( value.size, `is` (60) )
    }


}