package com.jiahan.fave.core.dependencyInjection.module;

import com.jiahan.fave.core.location.GetCityAPI;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class ApiModule {
    @Provides
    GetCityAPI provideGetCityAPI(Retrofit retrofit) {
        return retrofit.create(GetCityAPI.class);
    }
}