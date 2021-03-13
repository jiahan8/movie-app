package com.jiahan.fave.core.dependencyInjection.module;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jiahan.fave.core.common.LiveDataSharedPref;
import com.jiahan.fave.core.data.app.ApplicationDataManager;
import com.jiahan.fave.core.data.app.ApplicationDataManagerImpl;
import com.jiahan.fave.core.data.city.CityManager;
import com.jiahan.fave.core.data.city.CityManagerImpl;
import com.jiahan.fave.core.data.user.UserDataManager;
import com.jiahan.fave.core.data.user.UserDataManagerImpl;
import com.jiahan.fave.core.entity.User;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {

    public static final String USER_DATA_PREF  = "USER_DATA_PREF";
    public static final String APP_DATA_PREF   = "APP_DATA_PREF";
    private static final String EMPTY_DATA_PREF = "EMPTY_DATA_PREF";
    private static final String GLOBAL_APP_PREF   = "GLOBAL_APP_PREF";

    @Provides
    @Singleton
    SharedPreferences provideGlobalSharedPref(@NonNull final Context context) {
        return context.getSharedPreferences(GLOBAL_APP_PREF, Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    @Named(APP_DATA_PREF)
    SharedPreferences provideApplicationDataSharedPreferences(@NonNull final Context context) {
        return context.getSharedPreferences(APP_DATA_PREF, Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    @Named(USER_DATA_PREF)
    SharedPreferences provideUserDataSharedPreferences(@NonNull final Context context) {
        return context.getSharedPreferences(USER_DATA_PREF, Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    @Named(EMPTY_DATA_PREF)
    SharedPreferences provideEmptyDataSharedPreferences(@NonNull final Context context) {
        return context.getSharedPreferences(EMPTY_DATA_PREF, Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    ApplicationDataManager provideApplicationDataManager(@Named(APP_DATA_PREF) @NonNull final SharedPreferences sharedPreferences,
                                                         @NonNull final Gson gson) {
        return new ApplicationDataManagerImpl(new LiveDataSharedPref(sharedPreferences), gson);
    }

    @Provides
    @Singleton
    UserDataManager provideUserDataManager(@Named(USER_DATA_PREF) @NonNull final SharedPreferences sharedPreferences,
                                           @NonNull final Gson gson) {
        return new UserDataManagerImpl(new LiveDataSharedPref(sharedPreferences), gson, TypeToken.get(User.class));
    }

    @Provides
    @Singleton
    CityManager provideCityManager(@Named(APP_DATA_PREF) @NonNull final SharedPreferences sharedPreferences,
                                   @NonNull final Gson gson) {
        return new CityManagerImpl(new LiveDataSharedPref(sharedPreferences), gson );
    }
}
