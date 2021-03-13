package com.jiahan.fave.favecomponent.network;

import com.jiahan.fave.favecomponent.network.pojo.response.FiltersResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FiltersAPI {
    @GET("/api/fave/v5/{country_code}/filters")
    Observable<FiltersResponse> getFilters(@Path("country_code") String countryCode,
                                           @Query("type") String type,
                                           @Query("main_category_id") long mainCategoryId,
                                           @Query("filters") boolean filters);
}