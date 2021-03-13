package com.jiahan.fave.favecomponent.interactor;

import androidx.core.util.Pair;

import com.jiahan.fave.core.entity.City;
import com.jiahan.fave.core.entity.Coordinates;
import com.jiahan.fave.core.utils.Constant;
import com.jiahan.fave.favecomponent.entity.Assortment;
import com.jiahan.fave.favecomponent.entity.AssortmentDetail;
import com.jiahan.fave.favecomponent.network.AssortmentAPI;
import com.jiahan.fave.favecomponent.network.pojo.response.AssortmentTypeResponse;
import com.jiahan.fave.favecomponent.network.pojo.response.AssortmentsResponse;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class AssortmentInteractor extends DealInteractor {
    @Inject
    AssortmentAPI mAssortmentAPI;

    @Inject
    public AssortmentInteractor() {

    }

    public Observable<List<Assortment>> getAssortmentList() {
        return mCityManager.getCurrentCity()
                .flatMap(city -> mAssortmentAPI.getAssortments(city.country_code, city.id))
                .flatMap(assortmentsResponse -> Observable.just(assortmentsResponse.mAssortments));
    }

    public Observable<List<Assortment>> getExploreAssortmentList(final int page) {
        return Observable.zip(mCityManager.getCurrentCity(), mSelector.getAppLocation(), Pair::new)
                .flatMap(pair -> {
                    City city = pair.first;
                    if (city == null) {
                        return Observable.empty();
                    }
                    Coordinates coordinates = pair.second;
                    return mAssortmentAPI.getExploreAssortments(
                            city.country_code,
                            city.id,
                            page,
                            Constant.PAGINATION_LIMIT,
                            coordinates.getLatitude(),
                            coordinates.getLongitude());
                })
                .flatMap(assortmentsResponse -> Observable.just(assortmentsResponse.mAssortments));
    }

    public Observable<AssortmentsResponse> getCategoryAssortmentList(final long categoryId, final long appFilterId, final String type, final int page) {
        return mCityManager.getCurrentCity()
                .flatMap(city -> mAssortmentAPI.getCategoryAssortments(
                        city.country_code,
                        city.id,
                        categoryId,
                        appFilterId,
                        type,
                        page,
                        Constant.PAGINATION_LIMIT));
    }

    public Observable<AssortmentDetail> getAssortmentDetail(final long id) {
        return mCityManager.getCurrentCity()
                .flatMap(city -> mAssortmentAPI.getAssortmentDetail(city.country_code, id, city.id))
                .flatMap(assortmentDetailResponse -> Observable.just(assortmentDetailResponse.mAssortmentDetail));
    }

    public Observable<AssortmentTypeResponse> getAssortmentType(final long assortmentId,
                                                                final String type,
                                                                final int page) {
        return Observable.zip(mCityManager.getCurrentCity(), mSelector.getAppLocation(), Pair::new)
                .flatMap(pair -> {
                            City city = pair.first;
                            if (city == null) {
                                return Observable.empty();
                            }
                            Coordinates coordinates = pair.second;
                            return mAssortmentAPI.getAssortmentType(
                                    city.country_code,
                                    assortmentId,
                                    type,
                                    city.id,
                                    page,
                                    Constant.PAGINATION_LIMIT,
                                    coordinates.getLatitude(),
                                    coordinates.getLongitude());
                        }
                );
    }
}