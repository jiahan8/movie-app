package com.jiahan.fave.view;

import com.jiahan.fave.favecomponent.DebugInjector;
import com.jiahan.fave.favecomponent.FaveCoreApplication;

public class Application extends FaveCoreApplication {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void initDI(final FaveCoreApplication faveCoreApplication) {
        DebugInjector.initDebugDI(faveCoreApplication);
    }

}
