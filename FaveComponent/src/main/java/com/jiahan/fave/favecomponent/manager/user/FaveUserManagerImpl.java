package com.jiahan.fave.favecomponent.manager.user;

import androidx.annotation.NonNull;

import com.jiahan.fave.core.data.user.UserManagerImpl;
import com.jiahan.fave.core.data.user.UserStore;
import com.jiahan.fave.favecomponent.entity.FaveUser;

import io.reactivex.rxjava3.core.Observable;

public class FaveUserManagerImpl extends UserManagerImpl<FaveUser> implements FaveUserManager {

    public FaveUserManagerImpl(@NonNull final UserStore<FaveUser> userDiskStore, @NonNull final UserStore<FaveUser> userMemStore) {
        super(userDiskStore, userMemStore);
    }

    public FaveUserManagerImpl() {
        super();
    }

    @Override
    public Observable<FaveUser> refreshUser() {
        return super.refreshUser();
    }

    @Override
    public Observable<FaveUser> getCurrentUser() {
        return super.getCurrentUser();
    }

    @Override
    public void setUser(@NonNull final FaveUser user) {
        super.setUser(user);
    }

    @Override
    public void deleteUser() {
        super.deleteUser();
    }
}
