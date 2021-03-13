package com.jiahan.fave.favecomponent.network;

import com.jiahan.fave.favecomponent.network.pojo.response.ECardDetailResponse;
import com.jiahan.fave.favecomponent.network.pojo.response.ECardOnboardingResponse;
import com.jiahan.fave.favecomponent.network.pojo.response.ECardsResponse;
import com.jiahan.fave.favecomponent.network.pojo.response.HowItWorkResponse;
import com.jiahan.fave.favecomponent.network.pojo.response.MainCategoryResponse;
import com.jiahan.fave.favecomponent.network.pojo.response.OutletsResponse;
import com.jiahan.fave.favecomponent.network.pojo.response.SectionsResponse;

import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ECardAPI {
    @GET("/api/fave/v5/{country_code}/e_cards")
    Observable<ECardsResponse> getECards(@Path("country_code") String countryCode,
                                         @Query("main_category_id") long categoryId,
                                         @Query("app_filter_id") long appFilterId,
                                         @Query("city_id") long cityId,
                                         @Query("page") int page,
                                         @Query("limit") int limit,
                                         @Query("latitude") double latitude,
                                         @Query("longitude") double longitude,
                                         @QueryMap Map<String, String> filters);

    @GET("/api/fave/v5/{country_code}/e_cards/main_categories")
    Observable<MainCategoryResponse> getECardAppFilters(@Path("country_code") String countryCode);

    @GET("/api/fave/v5/{country_code}/e_cards/sections")
    Observable<SectionsResponse> getECardsSections(@Path("country_code") String countryCode,
                                                   @Query("city_id") long cityId,
                                                   @Query("page") int page,
                                                   @Query("limit") int limit);

    @GET("/api/fave/v5/{country_code}/e_cards/sections/{section_id}")
    Observable<ECardsResponse> getECardsSectionDetail(@Path("country_code") String countryCode,
                                                      @Path("section_id") long sectionId,
                                                      @Query("city_id") long cityId,
                                                      @Query("page") int page,
                                                      @Query("limit") int limit,
                                                      @Query("latitude") double latitude,
                                                      @Query("longitude") double longitude);

    @GET("/api/fave/v5/{country_code}/e_cards/sections/merchandise")
    Observable<ECardsResponse> getECardsMerchandise(@Path("country_code") String countryCode,
                                                    @Query("main_category_id") long categoryId,
                                                    @Query("app_filter_id") long appFilterId,
                                                    @Query("city_id") long cityId,
                                                    @Query("page") int page,
                                                    @Query("limit") int limit,
                                                    @Query("latitude") double latitude,
                                                    @Query("longitude") double longitude,
                                                    @QueryMap Map<String, String> filters);

    @GET("/api/fave/v5/{country_code}/e_cards/{e_card_id}/outlets")
    Observable<OutletsResponse> getECardOutlets(@Path("country_code") String countryCode,
                                                @Path("e_card_id") long eCardId,
                                                @Query("page") int page,
                                                @Query("latitude") double latitude,
                                                @Query("longitude") double longitude);

    @GET("/api/fave/v5/{country_code}/companies/{company_id}/e_cards")
    Observable<ECardDetailResponse> getECardCompany(@Path("country_code") String countryCode,
                                                    @Path("company_id") long companyId);

    @GET("/api/fave/v5/{country_code}/companies/company_e_cards")
    Observable<ECardDetailResponse> getECardCompanyById(@Path("country_code") String countryCode,
                                                        @Query("id") long ecardId);

    @GET("/api/fave/v5/{country_code}/how_it_works")
    Observable<HowItWorkResponse> getHowItWorks(@Path("country_code") String countryCode,
                                                @Query("type") String type);

    @GET("/api/fave/v4/cities/{city}/e_cards/onboarding")
    Observable<ECardOnboardingResponse> getECardOnboarding(@Path("city") String citySlug);
}