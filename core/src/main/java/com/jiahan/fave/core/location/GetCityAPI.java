package com.jiahan.fave.core.location;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetCityAPI {
    @GET("/api/fave/v3/location")
    Observable<CityByCoordinateResponse> getCityByCoordinate(@Query("latitude") Double latitude,
                                                             @Query("longitude") Double longitude);
}