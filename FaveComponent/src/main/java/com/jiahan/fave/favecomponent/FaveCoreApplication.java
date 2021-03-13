package com.jiahan.fave.favecomponent;

import com.jiahan.fave.core.common.CoreApplication;
import com.jiahan.fave.core.dependencyInjection.component.CoreApplicationComponent;
import com.jiahan.fave.core.dependencyInjection.component.DaggerCoreApplicationComponent;
import com.jiahan.fave.core.network.EmptyBodyInterceptor;
import com.jiahan.fave.favecomponent.di.DaggerFaveComponent;
import com.jiahan.fave.favecomponent.di.FaveComponent;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;

public class FaveCoreApplication extends CoreApplication {
    private FaveComponent mFaveComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initDI(this);
    }

    public static com.jiahan.fave.favecomponent.FaveCoreApplication getFaveInstance() {
        return (com.jiahan.fave.favecomponent.FaveCoreApplication) getInstance();
    }

    protected void initDI(com.jiahan.fave.favecomponent.FaveCoreApplication faveCoreApplication) {
        List<Interceptor> interceptors = new ArrayList<>();
        List<Interceptor> networkInterceptors = new ArrayList<>();

        interceptors.add(new EmptyBodyInterceptor());
        mCoreApplicationComponent = DaggerCoreApplicationComponent.builder()
                .application(this)
                .baseURL("https://api.myfave.com")
                .interceptor(interceptors)
                .networkInterceptor(networkInterceptors)
                .build();
        mCoreApplicationComponent.inject(this);

        mFaveComponent = DaggerFaveComponent.builder().coreApplicationComponent(mCoreApplicationComponent).build();
        mFaveComponent.inject(this);
    }

    public FaveComponent getFaveComponent() {
        return mFaveComponent;
    }

    protected void setCoreComponent(CoreApplicationComponent coreComponent) {
        mCoreApplicationComponent = coreComponent;
    }

    protected void setFaveComponent(FaveComponent faveComponent) {
        mFaveComponent = faveComponent;
    }

}
