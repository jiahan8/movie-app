package com.jiahan.fave.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.jiahan.fave.database.AppDatabase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class MoviesRepositoryTest{

    private lateinit var appDatabase: AppDatabase
    private var hiltRule = HiltAndroidRule(this)
    private val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val rule = RuleChain.outerRule(hiltRule).around(instantTaskExecutorRule)

    @Inject
    lateinit var repository: MoviesRepository

    @Before
    fun setup() {
        hiltRule.inject()

        val context = InstrumentationRegistry.getInstrumentation().targetContext
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()

//        repository =
    }

    @After
    fun tearDown() {
        appDatabase.close()
    }

    @Test
    fun getMovieLiveData() {

    }

    @Test
    fun getMovie() {

    }

    @Test
    fun getMovieDetail() {

    }


}
