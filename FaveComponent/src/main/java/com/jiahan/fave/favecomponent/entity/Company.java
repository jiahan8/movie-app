package com.jiahan.fave.favecomponent.entity;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Company extends BaseCompany {
    @SerializedName("total_outlets")
    public int mTotalOutlets;

    @SerializedName("establishment_list")
    public List<String> mEstablishmentList;

    @SerializedName("cuisine_list")
    public List<String> mCuisineList;

    @SerializedName("food_list")
    public List<String> mFoodList;

    @SerializedName("has_company_reviews")
    public boolean mHasCompanyReviews;

    @SerializedName("e_card_count")
    public int mEcardCount;

    @SerializedName("favorited")
    public boolean mFavourite;

    //region ECARD params, will be nullable
    @Nullable
    @SerializedName("gallery_images")
    public List<String> mGalleryImages;

    @Nullable
    @SerializedName("featured_image")
    public String featureImage;

    @SerializedName("purchase_count")
    public int mPurchaseCount;

    @Nullable
    @SerializedName("fine_print")
    public String mFinePrintHtmlSection;

    @Nullable
    @SerializedName("share")
    public Share mShare;

    @Nullable
    @SerializedName("e_cards")
    public List<ECard> eCardList;

    @Nullable
    @SerializedName("labels")
    public List<CompanyLabel> mCompanyLabel;
    //endregion ECARD
}