package com.jiahan.fave.favecomponent.di.module;

import com.jiahan.fave.favecomponent.network.AssortmentAPI;
import com.jiahan.fave.favecomponent.network.DealAPI;
import com.jiahan.fave.favecomponent.network.ECardAPI;
import com.jiahan.fave.favecomponent.network.ExploreAPI;
import com.jiahan.fave.favecomponent.network.FiltersAPI;
import com.jiahan.fave.favecomponent.network.LocationAPI;
import com.jiahan.fave.favecomponent.network.MainCategoryAPI;
import com.jiahan.fave.favecomponent.network.MovieAPI;
import com.jiahan.fave.favecomponent.network.MyFaveAPI;
import com.jiahan.fave.favecomponent.network.OutletAPI;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class ApiModule {
    @Provides
    OutletAPI provideOutletAPI(Retrofit retrofit) {
        return retrofit.create(OutletAPI.class);
    }

    @Provides
    DealAPI provideDealAPI(Retrofit retrofit) {
        return retrofit.create(DealAPI.class);
    }

    @Provides
    AssortmentAPI provideAssortmentAPI(Retrofit retrofit) {
        return retrofit.create(AssortmentAPI.class);
    }

    @Provides
    ExploreAPI provideExploreAPI(Retrofit retrofit) {
        return retrofit.create(ExploreAPI.class);
    }

    @Provides
    MainCategoryAPI provideMainCategoryAPI(Retrofit retrofit) {
        return retrofit.create(MainCategoryAPI.class);
    }

    @Provides
    LocationAPI provideLocationAPI(Retrofit retrofit) {
        return retrofit.create(LocationAPI.class);
    }

    @Provides
    ECardAPI provideECardAPI(Retrofit retrofit) {
        return retrofit.create(ECardAPI.class);
    }

    @Provides
    FiltersAPI provideFiltersAPI(Retrofit retrofit) {
        return retrofit.create(FiltersAPI.class);
    }

    @Provides
    MyFaveAPI provideMyFaveAPI(Retrofit retrofit){
        return retrofit.create(MyFaveAPI.class);
    }

    @Provides
    MovieAPI provideMovieAPI(Retrofit retrofit){
        return retrofit.create(MovieAPI.class);
    }

}