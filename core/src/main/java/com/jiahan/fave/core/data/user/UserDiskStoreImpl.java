package com.jiahan.fave.core.data.user;

import androidx.annotation.NonNull;

import com.jiahan.fave.core.entity.User;

import io.reactivex.rxjava3.core.Observable;

public class UserDiskStoreImpl<T extends User> implements UserStore<T> {
    private UserDataManager<T> mUserDataManager;

    public UserDiskStoreImpl(final UserDataManager userDataManager) {
        mUserDataManager = userDataManager;
    }

    @Override
    public Observable<T> loadUser() {
        final User user = mUserDataManager.user().get();
        if (user.mId == 0) {
            return Observable.empty();
        }
        return Observable.just((T) user);
    }

    @Override
    public void saveUser(@NonNull final T user) {
        mUserDataManager.user()
                .set(user);
    }
}