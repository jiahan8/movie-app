package com.jiahan.fave.favecomponent.interactor;

import androidx.core.util.Pair;

import com.jiahan.fave.core.entity.City;
import com.jiahan.fave.core.entity.Coordinates;
import com.jiahan.fave.core.utils.Constant;
import com.jiahan.fave.favecomponent.network.MyFaveAPI;
import com.jiahan.fave.favecomponent.network.pojo.response.CompaniesResponse;
import com.jiahan.fave.favecomponent.network.pojo.response.DealsResponse;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class MyFavesInteractor extends BaseInteractor {
    @Inject
    MyFaveAPI mMyFaveAPI;

    @Inject
    public MyFavesInteractor() {
    }

    public Observable<Object> favorited(final long outletId, final String type) {
        return mCityManager.getCurrentCity().flatMap(city ->
                mMyFaveAPI.favorited(
                        city.country_code,
                        outletId,
                        type));
    }

    public Observable<Object> unFavorited(final long outletId, final String type) {
        return mCityManager.getCurrentCity().flatMap(city ->
                mMyFaveAPI.unfavorited(
                        city.country_code,
                        outletId,
                        type));
    }

    public Observable<DealsResponse> getMyFaveDeals(int page) {
        return Observable.zip(mCityManager.getCurrentCity(), mSelector.getAppLocation(), Pair::new)
                .flatMap(pair -> {
                    City city = pair.first;
                    if (city == null) {
                        return Observable.empty();
                    }
                    Coordinates coordinates = pair.second;
                    return mMyFaveAPI.getMyFaveDeals(
                            city.country_code,
                            page,
                            Constant.PAGINATION_LIMIT,
                            coordinates.getLatitude(),
                            coordinates.getLongitude());
                });
    }

    public Observable<CompaniesResponse> getMyFaveCompanies(int page) {
        return Observable.zip(mCityManager.getCurrentCity(), mSelector.getAppLocation(), Pair::new)
                .flatMap(pair -> {
                    City city = pair.first;
                    if (city == null) {
                        return Observable.empty();
                    }
                    Coordinates coordinates = pair.second;
                    return mMyFaveAPI.getMyFaveCompanies(
                            city.country_code,
                            page,
                            Constant.PAGINATION_LIMIT,
                            coordinates.getLatitude(),
                            coordinates.getLongitude());
                });
    }
}