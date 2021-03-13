package com.jiahan.fave.core.data.user;

import androidx.annotation.NonNull;

import com.jiahan.fave.core.entity.User;

import io.reactivex.rxjava3.core.Observable;

//ok
public interface UserManager<T extends User> {
    Observable<T> refreshUser();

    Observable<T> getCurrentUser();

    void setUser(@NonNull T user);

    void deleteUser();
}