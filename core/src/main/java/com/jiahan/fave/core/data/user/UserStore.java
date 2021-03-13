package com.jiahan.fave.core.data.user;

import androidx.annotation.NonNull;

import com.jiahan.fave.core.entity.User;

import io.reactivex.rxjava3.core.Observable;

public interface UserStore<T extends User> {
    Observable<T> loadUser();

    void saveUser(@NonNull T user);
}