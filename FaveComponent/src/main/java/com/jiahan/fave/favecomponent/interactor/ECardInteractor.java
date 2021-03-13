package com.jiahan.fave.favecomponent.interactor;

import androidx.annotation.Nullable;
import androidx.core.util.Pair;

import com.jiahan.fave.core.entity.City;
import com.jiahan.fave.core.entity.Coordinates;
import com.jiahan.fave.core.utils.Constant;
import com.jiahan.fave.favecomponent.entity.BaseECard;
import com.jiahan.fave.favecomponent.entity.Company;
import com.jiahan.fave.favecomponent.entity.ECardOnboarding;
import com.jiahan.fave.favecomponent.entity.HowItWorkData;
import com.jiahan.fave.favecomponent.entity.MainCategory;
import com.jiahan.fave.favecomponent.entity.Section;
import com.jiahan.fave.favecomponent.network.ECardAPI;
import com.jiahan.fave.favecomponent.network.pojo.response.ECardsResponse;
import com.jiahan.fave.favecomponent.network.pojo.response.OutletsResponse;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class ECardInteractor extends MyFavesInteractor {
    @Inject
    ECardAPI mECardAPI;

    @Inject
    public ECardInteractor() {
    }

    public Observable<List<BaseECard>> getECards(final long categoryId, final long appFilterId, final int page, @Nullable final Map<String, String> filters) {
        return Observable.zip(mCityManager.getCurrentCity(), mSelector.getAppLocation(), Pair::new)
                .flatMap(pair -> {
                    City city = pair.first;
                    if (city == null) {
                        return Observable.empty();
                    }
                    Coordinates coordinates = pair.second;
                    return mECardAPI.getECards(
                            city.country_code,
                            categoryId,
                            appFilterId,
                            city.id,
                            page,
                            Constant.PAGINATION_LIMIT,
                            coordinates.getLatitude(),
                            coordinates.getLongitude(),
                            filters);
                })
                .flatMap(eCardsResponse -> Observable.just(eCardsResponse.mECards));
    }

    public Observable<List<MainCategory>> getECardAppFilters() {
        return mCityManager.getCurrentCity()
                .flatMap(city -> mECardAPI.getECardAppFilters(city.country_code))
                .flatMap(mainCategoryResponse -> Observable.just(mainCategoryResponse.mMainCategoryList));
    }

    public Observable<List<Section>> getECardsSections(final int page, final int limit) {
        return Observable.zip(mCityManager.getCurrentCity(), mSelector.getAppLocation(), Pair::new)
                .flatMap(pair -> {
                    City city = pair.first;
                    if (city == null) {
                        return Observable.empty();
                    }
                    return mECardAPI.getECardsSections(
                            city.country_code,
                            city.id,
                            page,
                            limit);
                })
                .flatMap(sectionsResponse -> Observable.just(sectionsResponse.mSectionList));
    }

    public Observable<ECardsResponse> getECardsSectionDetail(final long sectionId, final int page) {
        return Observable.zip(mCityManager.getCurrentCity(), mSelector.getAppLocation(), Pair::new)
                .flatMap(pair -> {
                    City city = pair.first;
                    if (city == null) {
                        return Observable.empty();
                    }
                    Coordinates coordinates = pair.second;
                    return mECardAPI.getECardsSectionDetail(
                            city.country_code,
                            sectionId,
                            city.id,
                            page,
                            Constant.PAGINATION_LIMIT,
                            coordinates.getLatitude(),
                            coordinates.getLongitude());
                });
    }

    public Observable<ECardsResponse> getECardsMerchandise(final long categoryId, final long appFilterId, final int page, @Nullable final Map<String, String> filters) {
        return Observable.zip(mCityManager.getCurrentCity(), mSelector.getAppLocation(), Pair::new)
                .flatMap(pair -> {
                    City city = pair.first;
                    if (city == null) {
                        return Observable.empty();
                    }
                    Coordinates coordinates = pair.second;
                    return mECardAPI.getECardsMerchandise(
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

    public Observable<OutletsResponse> getECardOutlets(long eCardId, int page) {
        return Observable.zip(mCityManager.getCurrentCity(), mSelector.getAppLocation(), Pair::new)
                .flatMap(pair -> {
                    City city = pair.first;
                    if (city == null) {
                        return Observable.empty();
                    }
                    Coordinates coordinates = pair.second;
                    return mECardAPI.getECardOutlets(
                            city.country_code,
                            eCardId,
                            page,
                            coordinates.getLatitude(),
                            coordinates.getLongitude()
                    );
                });
    }

    public Observable<Company> getECard(final long companyId) {
        return Observable.zip(mCityManager.getCurrentCity(), mSelector.getAppLocation(), Pair::new)
                .flatMap(pair -> {
                    City city = pair.first;
                    if (city == null) {
                        return Observable.empty();
                    }
                    return mECardAPI.getECardCompany(city.country_code, companyId);
                }).map(eCardDetailResponse -> eCardDetailResponse.mCompany);
    }

    public Observable<Company> getECardById(final long eCardId) {
        return Observable.zip(mCityManager.getCurrentCity(), mSelector.getAppLocation(), Pair::new)
                .flatMap(pair -> {
                    City city = pair.first;
                    if (city == null) {
                        return Observable.empty();
                    }
                    return mECardAPI.getECardCompanyById(city.country_code, eCardId);
                }).map(eCardDetailResponse -> eCardDetailResponse.mCompany);
    }

    public Observable<List<HowItWorkData>> getHowItWork(final String type) {
        return Observable.zip(mCityManager.getCurrentCity(), mSelector.getAppLocation(), Pair::new)
                .flatMap(pair -> {
                    City city = pair.first;
                    if (city == null) {
                        return Observable.empty();
                    }
                    return mECardAPI.getHowItWorks(city.country_code, type);
                }).map(howItWorkResponse -> howItWorkResponse.mItWorkDataList);
    }

    public Observable<List<ECardOnboarding>> getECardOnboarding() {
        return Observable.zip(mCityManager.getCurrentCity(), mSelector.getAppLocation(), Pair::new)
                .flatMap(pair -> {
                    City city = pair.first;
                    if (city == null) {
                        return Observable.empty();
                    }
                    return mECardAPI.getECardOnboarding(city.slug);
                }).map(eCardOnboardingResponse -> eCardOnboardingResponse.mECardOnboardingList);
    }
}