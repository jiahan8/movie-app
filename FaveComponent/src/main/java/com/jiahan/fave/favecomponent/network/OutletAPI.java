package com.jiahan.fave.favecomponent.network;

import com.jiahan.fave.favecomponent.entity.Outlet;
import com.jiahan.fave.favecomponent.network.pojo.response.AssortmentsResponse;
import com.jiahan.fave.favecomponent.network.pojo.response.DealsResponse;
import com.jiahan.fave.favecomponent.network.pojo.response.FoodMenuResponse;
import com.jiahan.fave.favecomponent.network.pojo.response.OutletsResponse;
import com.jiahan.fave.favecomponent.network.pojo.response.ReviewResponse;

import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface OutletAPI {
    @GET("/api/fave/v5/{country_code}/outlets/{id}")
    Observable<Outlet> getOutletById(@Path("country_code") String countryCode,
                                     @Path("id") long id,
                                     @Query("latitude") double latitude,
                                     @Query("longitude") double longitude);

    @GET("/api/fave/v5/{country_code}/outlets/{id}/listings")
    Observable<DealsResponse> getDealsByOutletId(@Path("country_code") String countryCode,
                                                 @Path("id") long id,
                                                 @Query("latitude") double latitude,
                                                 @Query("longitude") double longitude,
                                                 @Query("page") int page);

    @GET("/api/fave/v5/{country_code}/outlets/{id}/reviews")
    Observable<ReviewResponse> getReviews(@Path("country_code") String countryCode,
                                          @Path("id") long id,
                                          @Query("page") int page);

    @GET("/api/fave/v5/{country_code}/outlets/{id}/outlets")
    Observable<OutletsResponse> getOutlets(@Path("country_code") String countryCode,
                                           @Path("id") long id,
                                           @Query("page") int page,
                                           @Query("latitude") double latitude,
                                           @Query("longitude") double longitude);

    @GET("/api/fave/v5/{country_code}/outlets/promo_cashback")
    Observable<OutletsResponse> getPromoCashbackOutlets(@Path("country_code") String countryCode,
                                                        @Query("main_category_id") long categoryId,
                                                        @Query("app_filter_id") long appFilterId,
                                                        @Query("city_id") long cityId,
                                                        @Query("page") int page,
                                                        @Query("limit") int limit,
                                                        @Query("latitude") double latitude,
                                                        @Query("longitude") double longitude,
                                                        @QueryMap Map<String, String> filters);

    @GET("/api/fave/v5/{country_code}/outlets/{id}/menu_items")
    Observable<FoodMenuResponse> getMenu(@Path("country_code") String countryCode,
                                         @Path("id") long id);

    @GET("/api/fave/v5/{country_code}/outlets/{id}/assortments")
    Observable<AssortmentsResponse> getOutletAssortments(@Path("country_code") String countryCode,
                                                         @Path("id") long id,
                                                         @Query("city_id") long cityId,
                                                         @Query("page") int page,
                                                         @Query("limit") int limit);

    @GET("/api/fave/v5/{country_code}/outlets")
    Observable<OutletsResponse> getOutlets(@Path("country_code") String countryCode,
                                           @Query("main_category_id") long categoryId,
                                           @Query("app_filter_id") long appFilterId,
                                           @Query("city_id") long cityId,
                                           @Query("page") int page,
                                           @Query("limit") int limit,
                                           @Query("latitude") double latitude,
                                           @Query("longitude") double longitude,
                                           @QueryMap Map<String, String> filters);
}