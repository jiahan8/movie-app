package com.jiahan.fave.favecomponent.entity;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class AssortmentDetail extends Assortment {
    @SerializedName("description")
    public String mDescription;

    @SerializedName("share")
    @Nullable
    public Share mShare;

    @SerializedName("fine_print")
    @Nullable
    public String mFinePrint;

    @SerializedName("promo_code")
    @Nullable
    public PromoCode mPromoCode;
}