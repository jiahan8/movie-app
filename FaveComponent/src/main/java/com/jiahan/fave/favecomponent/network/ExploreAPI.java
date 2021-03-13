package com.jiahan.fave.favecomponent.network;

import com.jiahan.fave.favecomponent.network.pojo.response.AssortmentsResponse;
import com.jiahan.fave.favecomponent.network.pojo.response.ExploreResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ExploreAPI {
    @GET("/api/fave/v5/{country_code}/explore/fragments")
    Observable<ExploreResponse> getFragments(@Path("country_code") String countryCode,
                                             @Query("city_id") long cityId);

    @GET("/api/fave/v5/{country_code}/explore/assortments")
    Observable<AssortmentsResponse> getAssortments(@Path("country_code") String countryCode,
                                                   @Query("city_id") long cityId,
                                                   @Query("page") int page,
                                                   @Query("latitude") double latitude,
                                                   @Query("longitude") double longitude);

}