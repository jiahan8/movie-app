package com.jiahan.fave.favecomponent.interactor;

import com.jiahan.fave.core.data.city.CityManager;
import com.jiahan.fave.core.entity.Place;
import com.jiahan.fave.favecomponent.entity.Country;
import com.jiahan.fave.favecomponent.network.LocationAPI;
import com.jiahan.fave.favecomponent.network.pojo.response.SearchResultResponse;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class LocationInteractor extends BaseInteractor {
    @Inject
    LocationAPI mLocationAPI;

    @Inject
    CityManager mCityManager;

    @Inject
    public LocationInteractor() {

    }

    public Observable<List<Place>> getPopularPlaces() {
        return mCityManager.getCurrentCity()
                .flatMap(city -> mLocationAPI.getPopularPlaces(city.country_code, city.id))
                .flatMap(placeResponse -> Observable.just(placeResponse.mPlaceList));
    }

    public Observable<SearchResultResponse> getSearchResult(final String query) {
        return mCityManager.getCurrentCity()
                .flatMap(city -> mLocationAPI.getSearchResults(city.country_code, query));
    }

    public Observable<List<Country>> getCountries() {
        return mLocationAPI.getCountries()
                .flatMap(countryResponse -> Observable.just(countryResponse.mCountryList));
    }
}