package com.jiahan.fave.favecomponent.di.module;

import com.jiahan.fave.favecomponent.network.MovieAPI;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class ApiModule {

    @Provides
    MovieAPI provideMovieAPI(Retrofit retrofit){
        return retrofit.create(MovieAPI.class);
    }

}