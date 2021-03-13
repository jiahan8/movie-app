package com.jiahan.fave.favecomponent.di.module;

import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.jiahan.fave.core.common.LiveDataSharedPref;
import com.jiahan.fave.core.data.user.UserStore;
import com.jiahan.fave.favecomponent.entity.FaveUser;
import com.jiahan.fave.favecomponent.manager.app.FaveApplicationDataManager;
import com.jiahan.fave.favecomponent.manager.app.FaveApplicationDataManagerImpl;
import com.jiahan.fave.favecomponent.manager.user.FaveUserDataManager;
import com.jiahan.fave.favecomponent.manager.user.FaveUserDataManagerImpl;
import com.jiahan.fave.favecomponent.manager.user.FaveUserManager;
import com.jiahan.fave.favecomponent.manager.user.FaveUserManagerImpl;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

import static com.jiahan.fave.core.dependencyInjection.module.DataModule.APP_DATA_PREF;
import static com.jiahan.fave.core.dependencyInjection.module.DataModule.USER_DATA_PREF;

@Module
public class ManagerModule {

    @Provides
    FaveApplicationDataManager provideApplicationDataManager(@Named(APP_DATA_PREF) @NonNull final SharedPreferences sharedPreferences,
                                                             @NonNull final Gson gson) {
        return new FaveApplicationDataManagerImpl(new LiveDataSharedPref(sharedPreferences), gson);
    }

    @Provides
    FaveUserDataManager provideUserDataManager(@Named(USER_DATA_PREF) @NonNull final SharedPreferences sharedPreferences,
                                               @NonNull final Gson gson) {
        return new FaveUserDataManagerImpl(new LiveDataSharedPref(sharedPreferences), gson);
    }


    @Provides
    FaveUserManager provideUserManager(
            @NonNull @Named("diskStore") final UserStore<FaveUser> userDiskStore,
            @NonNull @Named("memStore") final UserStore<FaveUser> userMemStore) {
        return new FaveUserManagerImpl(userDiskStore, userMemStore);
    }


}
