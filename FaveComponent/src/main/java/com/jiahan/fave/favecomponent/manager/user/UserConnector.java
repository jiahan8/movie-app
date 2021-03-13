package com.jiahan.fave.favecomponent.manager.user;

import androidx.annotation.NonNull;

import com.jiahan.fave.core.entity.User;
import com.jiahan.fave.core.network.pojo.request.ImmutableUserLocationRequest;
import com.jiahan.fave.core.network.pojo.request.SessionRequest;
import com.jiahan.fave.core.network.pojo.request.UserLocationRequest;
import com.jiahan.fave.core.network.pojo.request.UserRequest;
import com.jiahan.fave.core.network.pojo.response.UserResponse;
import com.jiahan.fave.favecomponent.manager.app.FaveApplicationDataManager;
import com.jiahan.fave.favecomponent.network.UserAPI;

import java.util.Objects;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;

public class UserConnector {
    @NonNull
    private final UserAPI                    mUserAPI;
    @NonNull
    private final FaveApplicationDataManager mApplicationDataManager;

    @Inject
    public UserConnector(@NonNull final UserAPI userAPI,
                         @NonNull final FaveApplicationDataManager applicationDataManager) {
        mUserAPI = userAPI;
        mApplicationDataManager = applicationDataManager;
    }

    public Observable<UserResponse> getUser() {
        return mUserAPI.getUser();
    }

    public Observable<UserResponse> updateUser(@NonNull final UserRequest userRequest) {
        return mUserAPI.updateUser(userRequest);
    }

    public Observable<User> createUser(@NonNull final UserRequest userRequest) {
        return mUserAPI.createUser(userRequest)
                .flatMap( userResponse -> {
                    if (userResponse.user == null) {
                        return Observer::onComplete;
                    }
                    return Observable.just(Objects.requireNonNull(userResponse.user));
                });
    }

    public Observable<Object> updateUserLocation(final long userId, final double latitude, final double longitude) {
        final String deviceId = mApplicationDataManager.registrationId().get();
        UserLocationRequest request = ImmutableUserLocationRequest.builder()
                .device_id(deviceId)
                .user_id(userId)
                .latitude(latitude)
                .longitude(longitude)
                .build();
        return mUserAPI.updateUserLocation(request);
    }

    public Observable<UserResponse> verifyPhone(@NonNull final SessionRequest sessionRequest) {
        return mUserAPI.verifyPhone(sessionRequest);
    }

    public Observable<Object> deleteUser(@NonNull final String phone) {
        return mUserAPI.deleteUser(phone);
    }
}