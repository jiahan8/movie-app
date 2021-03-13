package com.jiahan.fave.core.data.user;

import androidx.annotation.NonNull;

import com.jiahan.fave.core.entity.User;

import io.reactivex.rxjava3.core.Observable;


public class UserManagerImpl<T extends User> implements UserManager<T> {
//    private UserAPI   mUserAPI;
    private UserStore<T> mUserDiskStore;
    private UserStore<T> mUserMemStore;


    public UserManagerImpl(  @NonNull final UserStore<T> userDiskStore,
                           @NonNull final UserStore<T> userMemStore) {
//        mUserAPI = retrofit.create(UserAPI.class);
        mUserDiskStore = userDiskStore;
        mUserMemStore = userMemStore;
    }

    public UserManagerImpl(){

    }

//    @Override
//    public Observable<User> refreshUser() {
//        return network();
//    }

    @Override
    public Observable<T> refreshUser() {
        return null;
    }

    @Override
    public Observable<T> getCurrentUser() {
        User emp = new User();
        return Observable.concat(memory(), local())
                .defaultIfEmpty((T)emp)
                .take(1);
    }


//    @Override
//    public Observable<User> getCurrentUser() {
//        return Observable.concat(memory(), local())
//                .defaultIfEmpty(ImmutableUser.builder().build())
//                .take(1);
//    }

    @Override
    public void setUser(@NonNull final T user) {
        deleteUser();
        mUserDiskStore.saveUser(user);
        mUserMemStore.saveUser(user);
    }

    @Override
    public void deleteUser() {
//        User user = ImmutableUser.builder()
//                .id(0)
//                .build();
//        mUserMemStore.saveUser(user);
//        mUserDiskStore.saveUser(user);
    }

    private Observable<T> memory() {
        return mUserMemStore.loadUser();
    }

    private Observable<T> local() {
        return mUserDiskStore.loadUser();
    }

//    private Observable<User> network() {
//        return mUserAPI.getUser()
//                .map(BaseUserResponse::user)
//                .doOnNext(user -> {
//                    mUserDiskStore.saveUser(user);
//                    mUserMemStore.saveUser(user);
//                });
//    }
}