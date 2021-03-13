package com.jiahan.fave.favecomponent.network;

import com.jiahan.fave.core.network.pojo.request.SessionRequest;
import com.jiahan.fave.core.network.pojo.request.UserLocationRequest;
import com.jiahan.fave.core.network.pojo.request.UserRequest;
import com.jiahan.fave.core.network.pojo.response.UserResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface UserAPI {
    @GET("/api/fave/v1/users/current_user")
    Observable<UserResponse> getUser();

    @PUT("/api/fave/v1/users/basic")
    Observable<UserResponse> updateUser(@Body UserRequest userRequest);

    @POST("/api/fave/v2/users")
    Observable<UserResponse> createUser(@Body UserRequest userRequest);

    @POST("/api/fave/v1/users/verify_phone")
    Observable<UserResponse> verifyPhone(@Body SessionRequest sessionRequest);

    @POST("/api/fave/v1/users/location")
    Observable<Object> updateUserLocation(@Body UserLocationRequest request);

    @DELETE("/api/fave/v1/users")
    Observable<Object> deleteUser(@Query("phone") String formattedPhoneNumber);
}