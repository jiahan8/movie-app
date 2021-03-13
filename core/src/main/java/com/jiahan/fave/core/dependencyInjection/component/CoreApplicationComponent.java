package com.jiahan.fave.core.dependencyInjection.component;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.jiahan.fave.core.common.CoreApplication;
import com.jiahan.fave.core.data.city.CityManager;
import com.jiahan.fave.core.data.location.LatLngParamSelector;
import com.jiahan.fave.core.dependencyInjection.module.ApiModule;
import com.jiahan.fave.core.dependencyInjection.module.ApplicationModule;
import com.jiahan.fave.core.dependencyInjection.module.DataModule;
import com.jiahan.fave.core.dependencyInjection.module.NetworkModule;
import com.jiahan.fave.core.dependencyInjection.module.TrackerModule;
import com.jiahan.fave.core.location.BaseLocationInteractor;
import com.jiahan.fave.core.tracker.EventSender;

import java.util.List;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import okhttp3.Interceptor;
import retrofit2.Retrofit;

import static com.jiahan.fave.core.dependencyInjection.module.DataModule.APP_DATA_PREF;
import static com.jiahan.fave.core.dependencyInjection.module.DataModule.USER_DATA_PREF;

@Singleton
@Component(modules = {ApplicationModule.class,
        NetworkModule.class,
        ApiModule.class,
        DataModule.class,
        TrackerModule.class})
public interface CoreApplicationComponent {
    void inject(CoreApplication application);

    CityManager provideCityManager();

    Retrofit provideRetrofit();

    Gson getGson();

    EventSender provideEventSender();

    LatLngParamSelector provideLatLngParamSelector();

    @Named(APP_DATA_PREF)
    SharedPreferences getSharedPref();

    @Named(USER_DATA_PREF)
    SharedPreferences getUserSharedPref();

    BaseLocationInteractor provideBaseLocationInteractor();

    @Component.Builder
    interface Builder {
        com.jiahan.fave.core.dependencyInjection.component.CoreApplicationComponent build();
        // @BindsInstance replaces Builder appModule(AppModule appModule)
        // And removes Constructor with Application AppModule(Application)

        @BindsInstance
        Builder application(CoreApplication application);

        @BindsInstance
        Builder baseURL(String baseURL);

        @BindsInstance
        Builder interceptor(@Named("Interceptors") List<Interceptor> interceptors);

        @BindsInstance
        Builder networkInterceptor(@Named("NetworkInterceptors") List<Interceptor> interceptors);
    }
}