package com.jiahan.fave.di

import com.jiahan.fave.repository.MoviesRepository
import com.jiahan.fave.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(moviesRepository: MoviesRepository): Repository

}

