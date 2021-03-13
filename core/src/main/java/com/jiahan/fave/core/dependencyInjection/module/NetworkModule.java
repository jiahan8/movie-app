package com.jiahan.fave.core.dependencyInjection.module;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.jiahan.fave.core.data.session.JWTSessionManagerImpl;
import com.jiahan.fave.core.data.session.SessionManager;
import com.jiahan.fave.core.data.user.UserDataManager;
import com.jiahan.fave.core.network.BaseOkHttp3RequestInterceptor;
import com.jiahan.fave.core.network.CustomSocketFactory;
import com.jiahan.fave.core.network.NetworkConstants;
import com.jiahan.fave.core.utils.VersionUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {
//    private final String            nBaseURL;
//    private final List<Interceptor> mInterceptors;
//    private final List<Interceptor> mNetworkInterceptors;
//
//    public NetworkModule(final String baseURL,
//                         final List<Interceptor> interceptors,
//                         final List<Interceptor> networkInterceptors) {
//        nBaseURL = baseURL;
//        mInterceptors = interceptors;
//        mNetworkInterceptors = networkInterceptors;
//    }

    @Singleton
    @Provides
    BaseOkHttp3RequestInterceptor provideBaseOkHttp3RequestInterceptor(@NonNull Context context) {
        return new BaseOkHttp3RequestInterceptor(context);
    }

    @Singleton
    @Provides
    JWTSessionManagerImpl provideJWTUserSessionManager(UserDataManager userDataManager) {
        return new JWTSessionManagerImpl(userDataManager);
    }

    @Singleton
    @Provides
    List<SessionManager<?>> provideUserSessionManagers(@NonNull final JWTSessionManagerImpl jwtUserSessionManager) {
        final List<SessionManager<?>> sessionManagers = new ArrayList<>(1);
        sessionManagers.add(jwtUserSessionManager);
        return sessionManagers;
    }

    @Singleton
    @Provides
    CustomSocketFactory provideTLSSocketFactory() {
        return new CustomSocketFactory();
    }

    /**
     * Application should use the same OkHttpClient because we can shared the cache
     *
     * @return OKHttpClient instance
     */
    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient(@NonNull CustomSocketFactory customSocketFactory,
                                     @NonNull Cache cache,
                                     @NonNull BaseOkHttp3RequestInterceptor baseInterceptor,
                                     @NonNull List<SessionManager<?>> sessionManagers,
                                     @Named("Interceptors") @NonNull List<Interceptor> interceptors,
                                     @Named("NetworkInterceptors") @NonNull List<Interceptor> networkInterceptors) {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(baseInterceptor)
                .connectTimeout(NetworkConstants.DEFAULT_CONNECT_TIMEOUT_MS, TimeUnit.MILLISECONDS)
                .readTimeout(NetworkConstants.DEFAULT_READ_TIMEOUT_MS, TimeUnit.MILLISECONDS);
        for (final SessionManager<?> sessionManager : sessionManagers) {
            final Interceptor sessionInterceptor = sessionManager.getOkHttpRequestInterceptor();
            builder.addInterceptor(sessionInterceptor);
        }
        for (Interceptor interceptor : interceptors)
            builder.addInterceptor(interceptor);
        for (Interceptor interceptor : networkInterceptors)
            builder.addNetworkInterceptor(interceptor);
        if (VersionUtil.isReleaseMode()) {
            builder.cache(cache);
        }
//        builder.sslSocketFactory(customSocketFactory.getSslSocketFactory(), customSocketFactory.getTrustManager());
        return builder.build();
    }

    @Singleton
    @Provides
    Cache provideCache(@NonNull Context context) {
        return new Cache(context.getCacheDir(), 10 * 1024 * 1024);
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(String baseURL, @NonNull OkHttpClient okHttpClient, @NonNull Gson gson) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(okHttpClient)
                .baseUrl(baseURL)
                .build();
    }
}
