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
class DetailViewModelTest {

    private lateinit var detailViewModel: DetailViewModel
    private lateinit var mainViewModel: MainViewModel
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

        detailViewModel = DetailViewModel( repository )
        mainViewModel = MainViewModel( repository )
    }

    @After
    fun tearDown() {
        appDatabase.close()
    }

    @Test
    fun getMovieDetail_returnCorrectMovie() {
        mainViewModel.getMovie(1)
        detailViewModel.getMovieDetail(2)

        val value = detailViewModel.movieDetail.getOrAwaitValue()

        assertThat( value.movie_id, `is` (2) )
    }

    
}