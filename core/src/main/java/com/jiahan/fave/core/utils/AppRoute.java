package com.jiahan.fave.core.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jiahan.fave.core.R;

import java.util.HashMap;

public class AppRoute {
    public static final String EXTRA_DEAL_ID               = "EXTRA_DEAL_ID";
    public static final String EXTRA_OUTLET_ID             = "EXTRA_OUTLET_ID";
    public static final String EXTRA_ASSORTMENT_ID         = "EXTRA_ASSORTMENT_ID";
    public static final String EXTRA_CATEGORY_ID           = "EXTRA_CATEGORY_ID";
    public static final String EXTRA_TITLE                 = "EXTRA_TITLE";
    public static final String EXTRA_CATEGORY_NAME         = "EXTRA_CATEGORY_NAME";
    public static final String EXTRA_CATEGORY              = "EXTRA_CATEGORY";
    public static final String EXTRA_PRODUCT               = "EXTRA_PRODUCT";
    public static final String EXTRA_PRODUCT_NAME          = "EXTRA_PRODUCT_NAME";
    public static final String EXTRA_APP_FILTER_ID         = "EXTRA_APP_FILTER_ID";
    public static final String EXTRA_SECTION_ID            = "EXTRA_SECTION_ID";
    public static final String EXTRA_SORTING               = "EXTRA_SORTING";
    public static final String EXTRA_FROM_EXPLORE          = "EXTRA_FROM_EXPLORE";
    public static final String EXTRA_TRENDING_DEAL         = "EXTRA_TRENDING_DEAL";
    public static final String EXTRA_RECOMMENDED_DEAL      = "EXTRA_RECOMMENDED_DEAL";
    public static final String EXTRA_PROMO_CASHBACK_OUTLET = "EXTRA_PROMO_CASHBACK_OUTLET";
    public static final String EXTRA_FROM_SCREEN           = "EXTRA_FROM_SCREEN";
    public static final String EXTRA_INDEX                 = "EXTRA_INDEX";
    public static final String EXTRA_TYPE                  = "EXTRA_TYPE";
    public static final String EXTRA_LIST_PAGE_NAME        = "EXTRA_LIST_PAGE_NAME";
    public static final String EXTRA_FILTER_PRESELECTED    = "EXTRA_FILTER_PRESELECTED";
    public static final String EXTRA_ECARD_ID              = "EXTRA_ECARD_ID";
    public static final String EXTRA_COMPANY_ID            = "EXTRA_COMPANY_ID";
    public static final String EXTRA_MERCHANDISE           = "EXTRA_MERCHANDISE";
    public static final String EXTRA_MOVIE_ID              = "EXTRA_MOVIE_ID";

    public static class OLD {
        public static Intent getFavePayActivityIntent(@NonNull final Context context, final long outletId, final long companyId) {
            Intent intent = new Intent();
            String className = "com.kfit.fave.activity.FavePayActivity";
            intent.setClassName(context, className);
            intent.putExtra(className + ".EXTRA_OUTLET_ID", String.valueOf(outletId));
            intent.putExtra(className + ".EXTRA_COMPANY_ID", String.valueOf(companyId));
            return intent;
        }

        public static Intent getECardRecipientDetailsActivityIntent(@NonNull final Context context, final long eCardId) {
            Intent intent = new Intent();
            String className = "com.kfit.fave.activity.ECardRecipientDetailsActivity";
            intent.setClassName(context, className);
            intent.putExtra(className + ".EXTRA_E_CARD_ID", String.valueOf(eCardId));
            return intent;
        }

        public static Intent getHowToRedeemActivityIntent(@NonNull final Context context, final String howToRedeemUrl) {
            Intent intent = new Intent();
            intent.setClassName(context, "com.kfit.fave.activity.HowToRedeemActivity");
            //todo change hardcoded url
            intent.putExtra("HowToRedeemActivity.EXTRA_URL", howToRedeemUrl);
            return intent;
        }

        public static Intent getConfirmationActivityIntent(@NonNull final Context context, final long dealId, final long outletId) {
            Intent intent = new Intent();
            intent.setClassName(context, "com.kfit.fave.activity.ConfirmationActivity");
            intent.putExtra("LUCID_EXTRA_DEAL_ID", String.valueOf(dealId));
            intent.putExtra("LUCID_EXTRA_OUTLET_ID", String.valueOf(outletId));
            return intent;
        }

        public static Intent getECardConfirmationActivityIntent(@NonNull final Context context, long ecardId) {
            String clazz = "com.kfit.fave.activity.ECardConfirmationActivity";
            Intent intent = new Intent();
            intent.setClassName(context, clazz);
            intent.putExtra(clazz + ".EXTRA_E_CARD_ID", ecardId);
            return intent;
        }

        public static Intent getSearchActivityIntent(@NonNull final Context context, @NonNull final String fromScreen) {
            String clazz = "com.kfit.fave.activity.SearchActivity";
            Intent intent = new Intent();
            intent.setClassName(context, clazz);
            intent.putExtra(clazz + ".EXTRA_FROM_FAVE_PAY", false);
            intent.putExtra(clazz + ".EXTRA_FROM_SCREEN_NAME", fromScreen);
            return intent;
        }

        public static Intent getECardSearchActivityIntent(@NonNull final Context context) {
            String clazz = "com.kfit.fave.activity.ECardSearchActivity";
            Intent intent = new Intent();
            intent.setClassName(context, clazz);
            return intent;
        }

        public static Intent getDeepLinkingActivityIntent(@NonNull final Context context, @NonNull final String deeplink) {
            Intent intent = new Intent();
            intent.setClassName(context, "com.kfit.fave.activity.DeepLinkingActivity");
            final Uri targetUri = Uri.parse(deeplink);
            intent.setData(targetUri);
            return intent;
        }

        public static Intent getTalkToUSFAQActivityIntent(@NonNull final Context context) {
            String clazz = "com.kfit.fave.activity.FaqActivity";
            Intent intent = new Intent();
            intent.setClassName(context, clazz);
            intent.putExtra(clazz + ".EXTRA_SHOW_TALK_TO_US", true);
            return intent;
        }
    }

    public static class Location {
        public static Intent getSearchLocationActivityIntent(@NonNull final Context context,
                                                             @NonNull final String fromScreen) {
            Intent intent = new Intent();
            intent.setClassName(context, "com.kfit.fave.location.feature.SearchLocationActivity");
            intent.putExtra(EXTRA_FROM_SCREEN, fromScreen);
            return intent;
        }

        public static Intent getChangeCityActivityIntent(@NonNull final Context context,
                                                         @NonNull final String fromScreen) {
            Intent intent = new Intent();
            intent.setClassName(context, "com.kfit.fave.location.feature.ChangeCityActivity");
            intent.putExtra(EXTRA_FROM_SCREEN, fromScreen);
            return intent;
        }
    }

    public static class DEAL {
        private static final String dealListClassName = "com.kfit.fave.deal.feature.listing.DealListActivity";

        public static Intent getDealDetailsActivityIntent(@NonNull final Context context,
                                                          @NonNull final Long dealId,
                                                          @NonNull final Long outletId) {
            Intent intent = new Intent();
            intent.setClassName(context, "com.kfit.fave.deal.feature.DealDetailActivity");
            intent.putExtra(EXTRA_DEAL_ID, dealId);
            intent.putExtra(EXTRA_OUTLET_ID, outletId);
            return intent;
        }

        public static Intent getDealListActivityIntent(@NonNull final Context context,
                                                       @NonNull final Long dealId,
                                                       @NonNull final Long outletId,
                                                       @NonNull final String title,
                                                       @NonNull final String listPageName) {
            Intent intent = new Intent();
            intent.setClassName(context, dealListClassName);
            intent.putExtra(EXTRA_DEAL_ID, dealId);
            intent.putExtra(EXTRA_OUTLET_ID, outletId);
            intent.putExtra(EXTRA_TITLE, title);
            intent.putExtra(EXTRA_LIST_PAGE_NAME, listPageName);
            return intent;
        }

        public static Intent getRecommendedDealListActivityIntent(@NonNull final Context context,
                                                                  @NonNull final Long dealId,
                                                                  @NonNull final String title,
                                                                  @NonNull final String listPageName) {
            Intent intent = new Intent();
            intent.setClassName(context, dealListClassName);
            intent.putExtra(EXTRA_DEAL_ID, dealId);
            intent.putExtra(EXTRA_TITLE, title);
            intent.putExtra(EXTRA_RECOMMENDED_DEAL, true);
            intent.putExtra(EXTRA_LIST_PAGE_NAME, listPageName);
            return intent;
        }

        public static Intent getTrendingDealListActivityIntent(@NonNull final Context context,
                                                               @NonNull final Long categoryId,
                                                               @NonNull final Long appFilterId,
                                                               @NonNull final String title,
                                                               @NonNull final String listPageName) {
            Intent intent = new Intent();
            intent.setClassName(context, dealListClassName);
            intent.putExtra(EXTRA_CATEGORY_ID, categoryId);
            intent.putExtra(EXTRA_APP_FILTER_ID, appFilterId);
            intent.putExtra(EXTRA_TITLE, title);
            intent.putExtra(EXTRA_TRENDING_DEAL, true);
            intent.putExtra(EXTRA_LIST_PAGE_NAME, listPageName);
            return intent;
        }

        public static Intent getDealReviewActivityIntent(@NonNull final Context context,
                                                         @NonNull final Long dealId) {
            Intent intent = new Intent();
            intent.setClassName(context, "com.kfit.fave.deal.feature.review.ReviewListActivity");
            intent.putExtra(EXTRA_DEAL_ID, dealId);
            return intent;
        }
    }

    public static class OUTLET {
        private static final String outletListClassName          = "com.kfit.fave.outlet.feature.listing.OutletListActivity";
        private static final String availableOutletListClassName = "com.kfit.fave.outlet.feature.listing.AvailableOutletListActivity";

        public static Intent getOutletDetailActivityIntent(@NonNull final Context context, @NonNull final Long outletId) {
            Intent intent = new Intent();
            intent.setClassName(context, "com.kfit.fave.outlet.feature.OutletDetailActivity");
            intent.putExtra(EXTRA_OUTLET_ID, outletId);
            return intent;
        }

        public static Intent getAvailableOutletListActivityIntent(@NonNull final Context context,
                                                                  @NonNull final Long outletId) {
            Intent intent = new Intent();
            intent.setClassName(context, availableOutletListClassName);
            intent.putExtra(EXTRA_OUTLET_ID, outletId);
            return intent;
        }

        public static Intent getAvailableOutletListCompanyActivityIntent(@NonNull final Context context,
                                                                         @NonNull final Long companyId) {
            Intent intent = new Intent();
            intent.setClassName(context, availableOutletListClassName);
            intent.putExtra(EXTRA_COMPANY_ID, companyId);
            return intent;
        }

        public static Intent getDealAvailableOutletListActivityIntent(@NonNull final Context context,
                                                                      @NonNull final Long dealId,
                                                                      @NonNull final Long outletId) {
            Intent intent = new Intent();
            intent.setClassName(context, availableOutletListClassName);
            intent.putExtra(EXTRA_DEAL_ID, dealId);
            intent.putExtra(EXTRA_OUTLET_ID, outletId);
            return intent;
        }

        public static Intent getECardAvailableOutletListActivityIntent(@NonNull final Context context,
                                                                       @NonNull final Long eCardId) {
            Intent intent = new Intent();
            intent.setClassName(context, availableOutletListClassName);
            intent.putExtra(EXTRA_ECARD_ID, eCardId);
            return intent;
        }

        public static Intent getOutletListActivityIntent(@NonNull final Context context,
                                                         @NonNull final Long categoryId,
                                                         @NonNull final Long appFilterId,
                                                         @NonNull final String listPageName) {
            Intent intent = new Intent();
            intent.setClassName(context, outletListClassName);
            intent.putExtra(EXTRA_CATEGORY_ID, categoryId);
            intent.putExtra(EXTRA_APP_FILTER_ID, appFilterId);
            intent.putExtra(EXTRA_LIST_PAGE_NAME, listPageName);
            return intent;
        }

        public static Intent getPromoCashbackOutletListActivityIntent(@NonNull final Context context,
                                                                      @NonNull final Long categoryId,
                                                                      @NonNull final Long appFilterId,
                                                                      @NonNull final String listPageName) {
            Intent intent = new Intent();
            intent.setClassName(context, outletListClassName);
            intent.putExtra(EXTRA_CATEGORY_ID, categoryId);
            intent.putExtra(EXTRA_APP_FILTER_ID, appFilterId);
            intent.putExtra(EXTRA_PROMO_CASHBACK_OUTLET, true);
            intent.putExtra(EXTRA_LIST_PAGE_NAME, listPageName);
            return intent;
        }
    }

    public static class ASSORTMENT {
        private static final String assortmentListClassName = "com.kfit.fave.assortment.feature.AssortmentListActivity";

        public static Intent getExploreAssortmentListActivityIntent(@NonNull final Context context) {
            Intent intent = new Intent();
            intent.setClassName(context, assortmentListClassName);
            intent.putExtra(EXTRA_FROM_EXPLORE, true);
            intent.putExtra(EXTRA_TITLE, context.getString(R.string.explore_places));
            return intent;
        }

        public static Intent getCategoryAssortmentListActivityIntent(@NonNull final Context context,
                                                                     @NonNull final Long categoryId,
                                                                     @NonNull final Long appFilterId,
                                                                     @NonNull final String type) {
            Intent intent = new Intent();
            intent.setClassName(context, assortmentListClassName);
            intent.putExtra(EXTRA_CATEGORY_ID, categoryId);
            intent.putExtra(EXTRA_APP_FILTER_ID, appFilterId);
            intent.putExtra(EXTRA_TYPE, type);
            intent.putExtra(EXTRA_TITLE, context.getString(R.string.collections));
            return intent;
        }

        public static Intent getDealAssortmentListActivityIntent(@NonNull final Context context,
                                                                 @NonNull final Long dealId,
                                                                 @NonNull final String title,
                                                                 @NonNull final String type) {
            Intent intent = new Intent();
            intent.setClassName(context, assortmentListClassName);
            intent.putExtra(EXTRA_DEAL_ID, dealId);
            intent.putExtra(EXTRA_TYPE, type);
            intent.putExtra(EXTRA_TITLE, title);
            return intent;
        }

        public static Intent getOutletAssortmentListActivityIntent(@NonNull final Context context,
                                                                   @NonNull final Long outletId,
                                                                   @NonNull final String title,
                                                                   @NonNull final String type) {
            Intent intent = new Intent();
            intent.setClassName(context, assortmentListClassName);
            intent.putExtra(EXTRA_OUTLET_ID, outletId);
            intent.putExtra(EXTRA_TYPE, type);
            intent.putExtra(EXTRA_TITLE, title);
            return intent;
        }

        public static Intent getAssortmentDetailActivityIntent(@NonNull final Context context,
                                                               @NonNull final Long assortmentId,
                                                               @NonNull final String type) {
            Intent intent = new Intent();
            intent.setClassName(context, "com.kfit.fave.assortment.feature.AssortmentDetailActivity");
            intent.putExtra(EXTRA_ASSORTMENT_ID, assortmentId);
            intent.putExtra(EXTRA_TYPE, type);
            return intent;
        }
    }

    public static class EXPLORE {
        public static Intent getExploreActivityIntent(@NonNull final Context context) {
            Intent intent = new Intent();
            intent.setClassName(context, "com.kfit.fave.explore.feature.ExploreActivity");
            return intent;
        }
    }

    public static class CATEGORY {
        public static Intent getMainCategoryActivityIntent(@NonNull final Context context,
                                                           @NonNull final Long categoryId,
                                                           @NonNull final String categoryName,
                                                           @Nullable final String type,
                                                           @Nullable final Long appFilterId,
                                                           @Nullable final String sorting) {
            Intent intent = new Intent();
            intent.setClassName(context, "com.kfit.fave.category.feature.MainCategoryActivity");
            intent.putExtra(EXTRA_CATEGORY_ID, categoryId);
            intent.putExtra(EXTRA_CATEGORY_NAME, categoryName);
            intent.putExtra(EXTRA_TYPE, type);
            intent.putExtra(EXTRA_APP_FILTER_ID, appFilterId);
            intent.putExtra(EXTRA_SORTING, sorting);
            return intent;
        }
    }

    public static class Filter {
        public static Intent getFilterActivityIntent(@NonNull final Context context,
                                                     @NonNull final String fromScreen,
                                                     @NonNull final Long categoryId,
                                                     @NonNull final String type,
                                                     @Nullable HashMap<String, Object> preSelected) {
            Intent intent = new Intent();
            intent.setClassName(context, "com.kfit.fave.filters.feature.FiltersActivity");
            intent.putExtra(EXTRA_FROM_SCREEN, fromScreen);
            intent.putExtra(EXTRA_CATEGORY_ID, categoryId);
            intent.putExtra(EXTRA_TYPE, type);
            intent.putExtra(EXTRA_FILTER_PRESELECTED, preSelected);
            return intent;
        }
    }

    public static class ECard {
        public static Intent getECardActivityIntent(@NonNull final Context context,
                                                    @Nullable final String categoryName,
                                                    @Nullable final Long appFilterId,
                                                    @Nullable final String sorting) {
            Intent intent = new Intent();
            intent.setClassName(context, "com.kfit.fave.ecard.feature.ECardActivity");
            intent.putExtra(EXTRA_CATEGORY_NAME, categoryName);
            intent.putExtra(EXTRA_APP_FILTER_ID, appFilterId);
            intent.putExtra(EXTRA_SORTING, sorting);
            return intent;
        }

        public static Intent getECardSectionListActivityIntent(@NonNull final Context context,
                                                               @NonNull final Long sectionId,
                                                               @NonNull final String title,
                                                               @NonNull final String listPageName) {
            Intent intent = new Intent();
            intent.setClassName(context, "com.kfit.fave.ecard.feature.listing.ECardListActivity");
            intent.putExtra(EXTRA_SECTION_ID, sectionId);
            intent.putExtra(EXTRA_TITLE, title);
            intent.putExtra(EXTRA_LIST_PAGE_NAME, listPageName);
            return intent;
        }

        public static Intent getECardMerchandiseListActivityIntent(@NonNull final Context context,
                                                                   @Nullable final Long categoryId,
                                                                   @Nullable final Long appFilterId,
                                                                   @NonNull final String title,
                                                                   @NonNull final String listPageName) {
            Intent intent = new Intent();
            intent.setClassName(context, "com.kfit.fave.ecard.feature.listing.ECardListActivity");
            intent.putExtra(EXTRA_CATEGORY_ID, categoryId);
            intent.putExtra(EXTRA_APP_FILTER_ID, appFilterId);
            intent.putExtra(EXTRA_TITLE, title);
            intent.putExtra(EXTRA_LIST_PAGE_NAME, listPageName);
            intent.putExtra(EXTRA_MERCHANDISE, true);
            return intent;
        }

        public static Intent getECardDetailActivityIntent(@NonNull final Context context,
                                                          @NonNull final Long ecardId,
                                                          @NonNull final Long companyId) {
            Intent intent = new Intent();
            intent.setClassName(context, "com.kfit.fave.ecard.feature.detail.ECardDetailActivity");
            intent.putExtra(EXTRA_ECARD_ID, ecardId);
            intent.putExtra(EXTRA_COMPANY_ID, companyId);
            return intent;
        }

        // type : e_cards , ..
        public static Intent getHowItWorkActivityIntent(@NonNull final Context context,
                                                        @NonNull final String type) {
            Intent intent = new Intent();
            intent.setClassName(context, "com.kfit.fave.ecard.feature.howItWork.HowItWorkActivity");
            intent.putExtra(EXTRA_TYPE, type);
            return intent;
        }
    }

    public static class Launcher {
        public static void Normal(Context context, Intent i) {
            context.startActivity(i);
        }
    }

    public static class MyFaves{
        private static final String myFavesClassName = "com.kfit.fave.myfaves.feature.MyFavesActivity";

        public static Intent getMyFaveActivity(@NonNull final Context context){
            return getMyFaveDealActivity(context);
        }

        public static Intent getMyFaveDealActivity(@NonNull final Context context){
            Intent intent = new Intent();
            intent.setClassName(context, myFavesClassName);
            intent.putExtra(EXTRA_INDEX, Constant.MY_FAVES_DEALS_INDEX);
            return intent;
        }

        public static Intent getMyFaveShopActivity(@NonNull final Context context){
            Intent intent = new Intent();
            intent.setClassName(context, myFavesClassName);
            intent.putExtra(EXTRA_INDEX, Constant.MY_FAVES_SHOPS_INDEX);
            return intent;
        }
    }

    public static class Movie{
        private static final String movieCLassName = "com.jiahan.fave.movie.feature.MovieListActivity";

        public static Intent getMovieListActivityIntent(@NonNull final Context context) {
            Intent intent = new Intent();
            intent.setClassName(context, movieCLassName);
            return intent;
        }

        public static Intent getMovieDetailActivityIntent(@NonNull final Context context,
                                                          @NonNull final int movieId) {
            Intent intent = new Intent();
            intent.setClassName(context, "com.jiahan.fave.movie.feature.MovieDetailActivity");
            intent.putExtra(EXTRA_MOVIE_ID, movieId);
            return intent;
        }

        public static Intent getMovieWebViewActivityIntent(@NonNull final Context context) {
            Intent intent = new Intent();
            intent.setClassName(context, "com.jiahan.fave.movie.feature.MovieWebViewActivity");
            return intent;
        }
    }
}