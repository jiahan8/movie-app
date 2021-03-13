package com.jiahan.fave.favecomponent.interactor;

import com.jiahan.fave.favecomponent.network.FiltersAPI;
import com.jiahan.fave.favecomponent.network.pojo.response.FiltersResponse;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class FiltersInteractor extends AssortmentInteractor {
    @Inject
    FiltersAPI mFiltersAPI;

    @Inject
    public FiltersInteractor() {
    }

    public Observable<FiltersResponse> getFilters(final String type, final long mainCategoryId, final boolean filters) {
        return mCityManager.getCurrentCity()
                .flatMap(city -> mFiltersAPI.getFilters(city.country_code, type, mainCategoryId, filters));
    }
}