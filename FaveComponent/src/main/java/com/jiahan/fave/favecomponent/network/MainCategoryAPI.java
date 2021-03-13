package com.jiahan.fave.favecomponent.network;

import com.jiahan.fave.favecomponent.network.pojo.response.BannerResponse;
import com.jiahan.fave.favecomponent.network.pojo.response.MainCategoryResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MainCategoryAPI {
    @GET("/api/fave/v5/{country_code}/main_categories/{main_category_id}/app_filters")
    Observable<MainCategoryResponse> getMainCategory(@Path("country_code") String countryCode,
                                                     @Path("main_category_id") long categoryId);

    @GET("/api/fave/v5/{country_code}/banners")
    Observable<BannerResponse> getBanners(@Path("country_code") String countryCode,
                                          @Query("city_id") long cityId,
                                          @Query("main_category_id") long categoryId,
                                          @Query("app_type") String appType,
                                          @Query("page") int page,
                                          @Query("limit") int limit);
}