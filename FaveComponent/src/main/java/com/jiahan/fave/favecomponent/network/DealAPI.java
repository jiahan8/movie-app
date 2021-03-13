package com.jiahan.fave.favecomponent.network;

import com.jiahan.fave.favecomponent.entity.Deal;
import com.jiahan.fave.favecomponent.network.pojo.response.AssortmentsResponse;
import com.jiahan.fave.favecomponent.network.pojo.response.DealsResponse;
import com.jiahan.fave.favecomponent.network.pojo.response.OutletsResponse;
import com.jiahan.fave.favecomponent.network.pojo.response.ReviewResponse;

import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface DealAPI {
    @GET("/api/fave/v5/{country_code}/deals/{id}")
    Observable<Deal> getDealById(@Path("country_code") String countryCode,
                                 @Path("id") long id,
                                 @Query("outlet_id") long outletId,
                                 @Query("latitude") double latitude,
                                 @Query("longitude") double longitude);

    @GET("/api/fave/v5/{country_code}/deals/{id}/reviews?page=1")
    Observable<ReviewResponse> getReviews(@Path("country_code") String countryCode,
                                          @Path("id") long id,
                                          @Query("page") int page);

    @GET("/api/fave/v5/{country_code}/deals/{id}/outlets")
    Observable<OutletsResponse> getDealOutlets(@Path("country_code") String countryCode,
                                               @Path("id") long id,
                                               @Query("outlet_id") long outletId,
                                               @Query("page") int page,
                                               @Query("latitude") double latitude,
                                               @Query("longitude") double longitude);

    @GET("/api/fave/v5/{country_code}/deals/{id}/assortments")
    Observable<AssortmentsResponse> getDealAssortments(@Path("country_code") String countryCode,
                                                       @Path("id") long id,
                                                       @Query("city_id") long cityId,
                                                       @Query("page") int page,
                                                       @Query("limit") int limit);

    @GET("/api/fave/v5/{country_code}/deals/recommended")
    Observable<DealsResponse> getRecommendedDeals(@Path("country_code") String countryCode,
                                                  @Query("deal_id") long dealId,
                                                  @Query("latitude") double latitude,
                                                  @Query("longitude") double longitude,
                                                  @Query("page") int page);

    @GET("/api/fave/v5/{country_code}/deals/trending")
    Observable<DealsResponse> getTrendingDeals(@Path("country_code") String countryCode,
                                               @Query("main_category_id") long categoryId,
                                               @Query("app_filter_id") long appFilterId,
                                               @Query("city_id") long cityId,
                                               @Query("page") int page,
                                               @Query("limit") int limit,
                                               @Query("latitude") double latitude,
                                               @Query("longitude") double longitude,
                                               @QueryMap Map<String, String> filters);

    @GET("/api/fave/v5/{country_code}/deals")
    Observable<DealsResponse> getDeals(@Path("country_code") String countryCode,
                                       @Query("main_category_id") long categoryId,
                                       @Query("app_filter_id") long appFilterId,
                                       @Query("city_id") long cityId,
                                       @Query("page") int page,
                                       @Query("limit") int limit,
                                       @Query("latitude") double latitude,
                                       @Query("longitude") double longitude,
                                       @QueryMap Map<String, String> filters);
}