package com.jiahan.fave.di

import android.content.Context
import com.jiahan.fave.database.AppDatabase
import com.jiahan.fave.database.MovieDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun provideMovieDAO(appDatabase: AppDatabase): MovieDAO {
        return appDatabase.movieDAO()
    }

}

