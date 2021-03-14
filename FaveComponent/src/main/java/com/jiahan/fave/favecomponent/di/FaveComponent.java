package com.jiahan.fave.favecomponent.di;

import com.jiahan.fave.core.common.CoreApplication;
import com.jiahan.fave.core.dependencyInjection.component.CoreApplicationComponent;
import com.jiahan.fave.core.tracker.EventSender;
import com.jiahan.fave.favecomponent.di.module.ApiModule;
import com.jiahan.fave.favecomponent.di.module.ManagerModule;
import com.jiahan.fave.favecomponent.interactor.MovieInteractor;
import com.jiahan.fave.favecomponent.manager.user.FaveUserDataManager;

import dagger.Component;

@FaveScope
@Component(modules = {ManagerModule.class, ApiModule.class},
        dependencies = CoreApplicationComponent.class)
public interface FaveComponent {
    void inject(CoreApplication application);

    FaveUserDataManager provideFaveUserDataManager();

    EventSender provideEventSender();

    MovieInteractor provideMovieInteractor();
}