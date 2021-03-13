package com.jiahan.fave.favecomponent.network;

import com.jiahan.fave.favecomponent.network.pojo.response.CountryResponse;
import com.jiahan.fave.favecomponent.network.pojo.response.PopularPlaceResponse;
import com.jiahan.fave.favecomponent.network.pojo.response.SearchResultResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface LocationAPI {
    @GET("/api/fave/v5/{country_code}/places/popular")
    Observable<PopularPlaceResponse> getPopularPlaces(@Path("country_code") String countryCode,
                                                      @Query("city_id") long cityId);

    @GET("/api/fave/v5/{country_code}/places/search")
    Observable<SearchResultResponse> getSearchResults(@Path("country_code") String countryCode,
                                                      @Query("query") String query);

    @GET("/api/fave/v5/countries")
    Observable<CountryResponse> getCountries();
}