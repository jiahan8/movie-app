package com.jiahan.fave.favecomponent.interactor;

import com.jiahan.fave.favecomponent.entity.Product;
import com.jiahan.fave.favecomponent.network.MainCategoryAPI;
import com.jiahan.fave.favecomponent.network.pojo.response.BannerResponse;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class MainCategoryInteractor extends FiltersInteractor {
    @Inject
    MainCategoryAPI mMainCategoryAPI;

    @Inject
    public MainCategoryInteractor() {

    }

    public Observable<List<Product>> getAppFilters(final long categoryId) {
        return mCityManager.getCurrentCity()
                .flatMap(city -> mMainCategoryAPI.getMainCategory(city.country_code, categoryId))
                .flatMap(mainCategoryResponse -> Observable.just(mainCategoryResponse.mProductList));
    }

    public Observable<BannerResponse> getBanners(final long categoryId , int page) {
        return mCityManager.getCurrentCity()
                .flatMap(city -> mMainCategoryAPI.getBanners(city.country_code, city.id, categoryId, "category_page",page, 10));
    }
}