package com.jiahan.fave.core.data.user;

import androidx.annotation.NonNull;

import com.jiahan.fave.core.entity.User;

import io.reactivex.rxjava3.core.Observable;


public class UserMemStoreImpl implements UserStore {
    private User mUser;

    @Override
    public Observable<User> loadUser() {
        if (mUser == null) {
            return Observable.empty();
        }
        return Observable.just(mUser);
    }

    @Override
    public void saveUser(@NonNull final User user) {
        mUser = user;
    }
}