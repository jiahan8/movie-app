package com.jiahan.fave.favecomponent.interactor;

import androidx.core.util.Pair;

import com.jiahan.fave.core.data.location.LatLngParamSelector;
import com.jiahan.fave.core.entity.City;
import com.jiahan.fave.core.entity.Coordinates;
import com.jiahan.fave.favecomponent.network.ExploreAPI;
import com.jiahan.fave.favecomponent.network.pojo.response.AssortmentsResponse;
import com.jiahan.fave.favecomponent.network.pojo.response.ExploreResponse;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class ExploreInteractor extends BaseInteractor {
    @Inject
    ExploreAPI mExploreAPI;

    @Inject
    LatLngParamSelector mSelector;

    @Inject
    public ExploreInteractor() {

    }

    public Observable<ExploreResponse> getFragmentList() {
        return mCityManager.getCurrentCity()
                .flatMap(city -> mExploreAPI.getFragments(city.country_code, city.id));
    }

    public Observable<AssortmentsResponse> getAssortmentList() {
        return Observable.zip(mCityManager.getCurrentCity(), mSelector.getAppLocation(), Pair::new)
                .flatMap(pair -> {
                    City city = pair.first;
                    if (city == null) {
                        return Observable.empty();
                    }
                    Coordinates coordinates = pair.second;
                    return mExploreAPI.getAssortments(city.country_code, city.id, 1, coordinates.getLatitude(), coordinates.getLongitude());
                });
    }
}