package com.jiahan.fave.favecomponent.network;

import com.jiahan.fave.favecomponent.network.pojo.response.CompaniesResponse;
import com.jiahan.fave.favecomponent.network.pojo.response.DealsResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MyFaveAPI {
    @FormUrlEncoded
    @POST("/api/fave/v5/{country_code}/favorites/")
    Observable<Object> favorited(@Path("country_code") String countryCode,
                                 @Field("id") long id,
                                 @Field("type") String type);

    @DELETE("/api/fave/v5/{country_code}/favorites/")
    Observable<Object> unfavorited(@Path("country_code") String countryCode,
                                   @Query("id") long id,
                                   @Query("type") String type);

    @GET("/api/fave/v5/{country_code}/favorites/deals")
    Observable<DealsResponse> getMyFaveDeals(@Path("country_code") String countryCode,
                                             @Query("page") int page,
                                             @Query("limit") int limit,
                                             @Query("latitude") double latitude,
                                             @Query("longitude") double longitude);

    @GET("/api/fave/v5/{country_code}/favorites/companies")
    Observable<CompaniesResponse> getMyFaveCompanies(@Path("country_code") String countryCode,
                                                     @Query("page") int page,
                                                     @Query("limit") int limit,
                                                     @Query("latitude") double latitude,
                                                     @Query("longitude") double longitude);
}