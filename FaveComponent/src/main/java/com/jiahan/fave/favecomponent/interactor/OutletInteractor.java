package com.jiahan.fave.favecomponent.interactor;

import androidx.core.util.Pair;

import com.jiahan.fave.core.entity.City;
import com.jiahan.fave.core.entity.Coordinates;
import com.jiahan.fave.core.utils.Constant;
import com.jiahan.fave.favecomponent.entity.Menu;
import com.jiahan.fave.favecomponent.entity.Outlet;
import com.jiahan.fave.favecomponent.network.OutletAPI;
import com.jiahan.fave.favecomponent.network.pojo.response.AssortmentsResponse;
import com.jiahan.fave.favecomponent.network.pojo.response.DealsResponse;
import com.jiahan.fave.favecomponent.network.pojo.response.OutletsResponse;
import com.jiahan.fave.favecomponent.network.pojo.response.ReviewResponse;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class OutletInteractor extends ECardInteractor {
    @Inject
    OutletAPI mOutletAPI;

    @Inject
    public OutletInteractor() {
    }

    public Observable<Outlet> getOutlet(long outletId) {
        return Observable.zip(mCityManager.getCurrentCity(), mSelector.getUserLocation(), Pair::new)
                .flatMap(pair -> {
                    City city = pair.first;
                    if (city == null) {
                        return Observable.empty();
                    }
                    Coordinates coordinates = pair.second;
                    return mOutletAPI.getOutletById(city.country_code,
                            outletId,
                            coordinates.getLatitude(),
                            coordinates.getLongitude()
                    );
                });
    }

    public Observable<DealsResponse> getOutletDeals(long outletId, int page) {
        return Observable.zip(mCityManager.getCurrentCity(), mSelector.getUserLocation(), Pair::new)
                .flatMap(pair -> {
                    City city = pair.first;
                    if (city == null) {
                        return Observable.empty();
                    }
                    Coordinates coordinates = pair.second;
                    return mOutletAPI.getDealsByOutletId(city.country_code,
                            outletId,
                            coordinates.getLatitude(),
                            coordinates.getLongitude(),
                            page);
                });
    }

    public Observable<OutletsResponse> getPromoCashbackOutlets(final long categoryId, final long appFilterId, final int page, final Map<String, String> filters) {
        return Observable.zip(mCityManager.getCurrentCity(), mSelector.getUserLocation(), Pair::new)
                .flatMap(pair -> {
                    City city = pair.first;
                    if (city == null) {
                        return Observable.empty();
                    }
                    Coordinates coordinates = pair.second;
                    return mOutletAPI.getPromoCashbackOutlets(
                            city.country_code,
                            categoryId,
                            appFilterId,
                            city.id,
                            page,
                            Constant.PAGINATION_LIMIT,
                            coordinates.getLatitude(),
                            coordinates.getLongitude(),
                            filters);
                });
    }

    public Observable<ReviewResponse> getReviews(long outletId, int page) {
        return mCityManager.getCurrentCity().flatMap(city ->
                mOutletAPI.getReviews(city.country_code, outletId, page));
    }

    public Observable<OutletsResponse> getOtherOutlets(long outletId, int page) {
        return Observable.zip(mCityManager.getCurrentCity(), mSelector.getUserLocation(), Pair::new)
                .flatMap(pair -> {
                    City city = pair.first;
                    Coordinates coordinates = pair.second;
                    return mOutletAPI.getOutlets(
                            city.country_code,
                            outletId,
                            page,
                            coordinates.getLatitude(),
                            coordinates.getLongitude()
                    );
                });
    }

    public Observable<List<Menu>> getMenu(long outletId) {
        return mCityManager.getCurrentCity()
                .flatMap(city -> mOutletAPI.getMenu(
                        city.country_code,
                        outletId))
                .flatMap(foodMenuResponse -> Observable.just(foodMenuResponse.mMenuList));
    }

    public Observable<AssortmentsResponse> getOutletAssortments(final long outletId, final int page) {
        return mCityManager.getCurrentCity()
                .flatMap(city -> mOutletAPI.getOutletAssortments(
                        city.country_code,
                        outletId,
                        city.id,
                        page,
                        Constant.PAGINATION_LIMIT));
    }

    public Observable<OutletsResponse> getOutlets(final long categoryId, final long appFilterId, final int page, final Map<String, String> filters) {
        return Observable.zip(mCityManager.getCurrentCity(), mSelector.getUserLocation(), Pair::new)
                .flatMap(pair -> {
                    City city = pair.first;
                    if (city == null) {
                        return Observable.empty();
                    }
                    Coordinates coordinates = pair.second;
                    return mOutletAPI.getOutlets(
                            city.country_code,
                            categoryId,
                            appFilterId,
                            city.id,
                            page,
                            Constant.PAGINATION_LIMIT,
                            coordinates.getLatitude(),
                            coordinates.getLongitude(),
                            filters);
                });
    }
}