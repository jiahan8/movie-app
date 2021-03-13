package com.jiahan.fave.favecomponent.interactor;

import androidx.core.util.Pair;

import com.jiahan.fave.core.entity.City;
import com.jiahan.fave.core.entity.Coordinates;
import com.jiahan.fave.core.utils.Constant;
import com.jiahan.fave.favecomponent.entity.Deal;
import com.jiahan.fave.favecomponent.network.DealAPI;
import com.jiahan.fave.favecomponent.network.pojo.response.AssortmentsResponse;
import com.jiahan.fave.favecomponent.network.pojo.response.DealsResponse;
import com.jiahan.fave.favecomponent.network.pojo.response.OutletsResponse;
import com.jiahan.fave.favecomponent.network.pojo.response.ReviewResponse;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class DealInteractor extends OutletInteractor {
    @Inject
    DealAPI mDealAPI;

    @Inject
    public DealInteractor() {

    }

    public Observable<Deal> getDeal(final long dealId, final long outletId) {
        return Observable.zip(mCityManager.getCurrentCity(), mSelector.getUserLocation(), Pair::new)
                .flatMap(pair -> {
                    City city = pair.first;
                    if (city == null) {
                        return Observable.empty();
                    }
                    Coordinates coordinates = pair.second;
                    return mDealAPI.getDealById(city.country_code,
                            dealId,
                            outletId,
                            coordinates.getLatitude(),
                            coordinates.getLongitude());
                });
    }

    public Observable<ReviewResponse> getReviews(long dealId, int page) {
        return mCityManager.getCurrentCity().flatMap(city ->
                mDealAPI.getReviews(city.country_code, dealId, page));
    }

    public Observable<OutletsResponse> getDealOutlets(final long dealId, final long outletId, final int page) {
        return Observable.zip(mCityManager.getCurrentCity(), mSelector.getAppLocation(), Pair::new)
                .flatMap(pair -> {
                    City city = pair.first;
                    if (city == null) {
                        return Observable.empty();
                    }
                    Coordinates coordinates = pair.second;
                    return mDealAPI.getDealOutlets(city.country_code,
                            dealId,
                            outletId,
                            page,
                            coordinates.getLatitude(),
                            coordinates.getLongitude()
                    );
                });
    }

    public Observable<AssortmentsResponse> getDealAssortments(final long dealId, final int page) {
        return mCityManager.getCurrentCity()
                .flatMap(city -> mDealAPI.getDealAssortments(
                        city.country_code,
                        dealId,
                        city.id,
                        page,
                        Constant.PAGINATION_LIMIT));
    }

    public Observable<DealsResponse> getDeals(final long categoryId, final long appFilterId, final int page, final Map<String, String> filters) {
        return Observable.zip(mCityManager.getCurrentCity(), mSelector.getAppLocation(), Pair::new)
                .flatMap(pair -> {
                    City city = pair.first;
                    if (city == null) {
                        return Observable.empty();
                    }
                    Coordinates coordinates = pair.second;
                    return mDealAPI.getDeals(
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

    public Observable<DealsResponse> getRecommendedDeals(long dealId, int page) {
        return Observable.zip(mCityManager.getCurrentCity(), mSelector.getAppLocation(), Pair::new)
                .flatMap(pair -> {
                    City city = pair.first;
                    if (city == null) {
                        return Observable.empty();
                    }
                    Coordinates coordinates = pair.second;
                    return mDealAPI.getRecommendedDeals(city.country_code,
                            dealId,
                            coordinates.getLatitude(),
                            coordinates.getLongitude(),
                            page);
                });
    }

    public Observable<DealsResponse> getTrendingDeals(final long categoryId, final long appFilterId, final int page, final Map<String, String> filters) {
        return Observable.zip(mCityManager.getCurrentCity(), mSelector.getAppLocation(), Pair::new)
                .flatMap(pair -> {
                    City city = pair.first;
                    if (city == null) {
                        return Observable.empty();
                    }
                    Coordinates coordinates = pair.second;
                    return mDealAPI.getTrendingDeals(
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