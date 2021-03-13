package com.jiahan.fave.favecomponent.di;

import com.jiahan.fave.core.common.CoreApplication;
import com.jiahan.fave.core.data.city.CityManager;
import com.jiahan.fave.core.data.location.LatLngParamSelector;
import com.jiahan.fave.core.dependencyInjection.component.CoreApplicationComponent;
import com.jiahan.fave.core.location.BaseLocationInteractor;
import com.jiahan.fave.core.tracker.EventSender;
import com.jiahan.fave.favecomponent.di.module.ApiModule;
import com.jiahan.fave.favecomponent.di.module.ManagerModule;
import com.jiahan.fave.favecomponent.interactor.AssortmentInteractor;
import com.jiahan.fave.favecomponent.interactor.DealInteractor;
import com.jiahan.fave.favecomponent.interactor.ECardInteractor;
import com.jiahan.fave.favecomponent.interactor.ExploreInteractor;
import com.jiahan.fave.favecomponent.interactor.FiltersInteractor;
import com.jiahan.fave.favecomponent.interactor.LocationInteractor;
import com.jiahan.fave.favecomponent.interactor.MainCategoryInteractor;
import com.jiahan.fave.favecomponent.interactor.MovieInteractor;
import com.jiahan.fave.favecomponent.interactor.MyFavesInteractor;
import com.jiahan.fave.favecomponent.interactor.OutletInteractor;
import com.jiahan.fave.favecomponent.manager.app.FaveApplicationDataManager;
import com.jiahan.fave.favecomponent.manager.user.FaveUserDataManager;

import dagger.Component;

@FaveScope
@Component(modules = {ManagerModule.class, ApiModule.class},
        dependencies = CoreApplicationComponent.class)
public interface FaveComponent {
    void inject(CoreApplication application);

    CityManager provideCityManager();

    FaveUserDataManager provideFaveUserDataManager();

    FaveApplicationDataManager provideFaveApplicationDataManager();

    EventSender provideEventSender();

    LatLngParamSelector provideLatLngParamSelector();

    OutletInteractor provideOutletInteractor();

    DealInteractor provideDealInteractor();

    AssortmentInteractor provideAssortmentInteractor();

    ExploreInteractor provideExploreInteractor();

    MainCategoryInteractor provideMainCategoryInteractor();

    LocationInteractor provideLocationInteractor();

    ECardInteractor provideECardInteractor();

    BaseLocationInteractor provideBaseLocationInteractor();

    FiltersInteractor provideFiltersInteractor();

    MyFavesInteractor provideMyFavesInteractor();

    MovieInteractor provideMovieInteractor();
}