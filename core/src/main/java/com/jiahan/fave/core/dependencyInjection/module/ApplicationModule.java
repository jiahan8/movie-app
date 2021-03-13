package com.jiahan.fave.core.dependencyInjection.module;

import android.content.Context;
import android.content.res.Resources;

import androidx.annotation.NonNull;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.gson.Gson;
//import com.huawei.hms.api.HuaweiApiAvailability;
import com.jiahan.fave.core.common.CoreApplication;
import com.jiahan.fave.core.data.app.ApplicationDataManager;
import com.jiahan.fave.core.data.city.CityDiskStoreImpl;
import com.jiahan.fave.core.data.city.CityManager;
import com.jiahan.fave.core.data.city.CityMemStoreImpl;
import com.jiahan.fave.core.data.city.CityStore;
import com.jiahan.fave.core.data.location.DefaultLocationClientAPI;
import com.jiahan.fave.core.data.location.GMSLocationClientAPI;
//import com.jiahan.fave.core.data.location.HMSLocationClientAPI;
import com.jiahan.fave.core.data.location.LatLngParamSelector;
import com.jiahan.fave.core.data.location.LocationClientAPI;
import com.jiahan.fave.core.data.user.UserDataManager;
import com.jiahan.fave.core.data.user.UserDiskStoreImpl;
import com.jiahan.fave.core.data.user.UserManager;
import com.jiahan.fave.core.data.user.UserManagerImpl;
import com.jiahan.fave.core.data.user.UserMemStoreImpl;
import com.jiahan.fave.core.data.user.UserStore;
import com.jiahan.fave.core.entity.User;
import com.jiahan.fave.core.utils.gson.GsonUtils;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    @Provides
    @Singleton
    Context provideContext(CoreApplication application) {
        return application;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return GsonUtils.getGson();
    }

    @Provides
    @Singleton
    UserManager provideUserManagerImpl(@NonNull @Named("diskStore") final UserStore userDiskStore,
                                       @NonNull @Named("memStore") final UserStore userMemStore) {
        return new UserManagerImpl<User>(userDiskStore, userMemStore);
    }

    @Provides
    @Singleton
    LocationClientAPI provideLocationClientAPI(@NonNull final Context context) {
        if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) == ConnectionResult.SUCCESS) {
            return new GMSLocationClientAPI(context);
        }
//        if (HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(context) == com.huawei.hms.api.ConnectionResult.SUCCESS) {
//            return new HMSLocationClientAPI(context);
//        }
        return new DefaultLocationClientAPI(context);
    }

    @Provides
    @Singleton
    @Named("diskStore")
    CityStore provideCityStore(ApplicationDataManager applicationDataManager) {
        return new CityDiskStoreImpl(applicationDataManager);
    }

    @Provides
    @Singleton
    @Named("memStore")
    CityStore provideCityMemStore() {
        return new CityMemStoreImpl();
    }

    @Provides
    @Singleton
    @Named("diskStore")
    UserStore provideUserDiskStore(@NonNull final UserDataManager userDataManager) {
        return new UserDiskStoreImpl(userDataManager);
    }

    @Provides
    @Singleton
    @Named("memStore")
    UserStore provideUserMemStore() {
        return new UserMemStoreImpl();
    }

    @Provides
    @Singleton
    LatLngParamSelector provideLatLngParamSelector(final LocationClientAPI locationClientAPI,
                                                   final CityManager cityManager,
                                                   final ApplicationDataManager applicationDataManager) {
        return new LatLngParamSelector(locationClientAPI, cityManager, applicationDataManager);
    }

    @Provides
    Resources provideResources(Context context) {
        return context.getResources();
    }
}