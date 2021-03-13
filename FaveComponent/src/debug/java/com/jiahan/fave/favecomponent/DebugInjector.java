package com.jiahan.fave.favecomponent;

import android.content.Context;
import android.content.SharedPreferences;

import com.chuckerteam.chucker.api.ChuckerInterceptor;
import com.facebook.stetho.Stetho;
import com.jiahan.fave.core.dependencyInjection.component.CoreApplicationComponent;
import com.jiahan.fave.core.dependencyInjection.component.DaggerCoreApplicationComponent;
import com.jiahan.fave.core.network.EmptyBodyInterceptor;
import com.jiahan.fave.core.utils.VersionUtil;
import com.jiahan.fave.favecomponent.di.DaggerFaveComponent;
import com.jiahan.fave.favecomponent.di.FaveComponent;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.logging.HttpLoggingInterceptor;

public class DebugInjector {
    public static void initDebugDI(FaveCoreApplication application){

        List<Interceptor> interceptors = new ArrayList<>();
        List<Interceptor> networkInterceptors = new ArrayList<>();

        Stetho.initializeWithDefaults(application);
        networkInterceptors.add(new com.facebook.stetho.okhttp3.StethoInterceptor());

        interceptors.add(new ChuckerInterceptor(application));
        interceptors.add(new EmptyBodyInterceptor());

        if (!VersionUtil.isReleaseMode()) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
            interceptors.add(loggingInterceptor);
        }
        SharedPreferences mSharedPreferences = application.getApplicationContext().getSharedPreferences("com.jiahan.fave.util.DebugSettings.SHARED_PREF", Context.MODE_PRIVATE);
        String baseUrl = mSharedPreferences.getString("KEY_MAIN_SERVER_ENDPOINT", "http://api.themoviedb.org/3/");

        CoreApplicationComponent mCoreApplicationComponent = DaggerCoreApplicationComponent.builder()
                .application(application)
                .baseURL(baseUrl)
                .interceptor(interceptors)
                .networkInterceptor(networkInterceptors)
                .build();
        application.setCoreComponent(mCoreApplicationComponent);
        mCoreApplicationComponent.inject(application);
        FaveComponent mFaveComponent = DaggerFaveComponent.builder().coreApplicationComponent(mCoreApplicationComponent).build();
        application.setFaveComponent(mFaveComponent);
        mFaveComponent.inject(application);
    }
}
