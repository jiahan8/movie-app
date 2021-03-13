package com.jiahan.fave.favecomponent.network;

import com.jiahan.fave.favecomponent.network.pojo.response.AssortmentDetailResponse;
import com.jiahan.fave.favecomponent.network.pojo.response.AssortmentTypeResponse;
import com.jiahan.fave.favecomponent.network.pojo.response.AssortmentsResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AssortmentAPI {
    @GET("/api/fave/v5/{country_code}/assortments")
    Observable<AssortmentsResponse> getAssortments(@Path("country_code") String countryCode, @Query("city_id") long cityId);

    @GET("/api/fave/v5/{country_code}/explore/assortments")
    Observable<AssortmentsResponse> getExploreAssortments(@Path("country_code") String countryCode,
                                                          @Query("city_id") long cityId,
                                                          @Query("page") int page,
                                                          @Query("limit") int limit,
                                                          @Query("latitude") double latitude,
                                                          @Query("longitude") double longitude);

    @GET("/api/fave/v5/{country_code}/assortments")
    Observable<AssortmentsResponse> getCategoryAssortments(@Path("country_code") String countryCode,
                                                           @Query("city_id") long cityId,
                                                           @Query("main_category_id") long categoryId,
                                                           @Query("app_filter_id") long appFilterId,
                                                           @Query("type") String type,
                                                           @Query("page") int page,
                                                           @Query("limit") int limit);

    @GET("/api/fave/v5/{country_code}/assortments/{id}")
    Observable<AssortmentDetailResponse> getAssortmentDetail(@Path("country_code") String countryCode,
                                                             @Path("id") long assortmentId,
                                                             @Query("city_id") long cityId);

    @GET("/api/fave/v5/{country_code}/assortments/{id}/{type}")
    Observable<AssortmentTypeResponse> getAssortmentType(@Path("country_code") String countryCode,
                                                         @Path("id") long assortmentId,
                                                         @Path("type") String assortmentType,
                                                         @Query("city_id") long cityId,
                                                         @Query("page") int page,
                                                         @Query("limit") int limit,
                                                         @Query("latitude") double latitude,
                                                         @Query("longitude") double longitude);
}