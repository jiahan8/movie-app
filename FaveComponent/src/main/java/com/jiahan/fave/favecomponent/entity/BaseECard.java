package com.jiahan.fave.favecomponent.entity;

import androidx.annotation.Keep;

import com.google.gson.annotations.SerializedName;

public class BaseECard {
    @SerializedName("id")
    public long mId;

    @SerializedName("total_value")
    public String mValue;

    @SerializedName("company_id")
    public long mCompanyId;

    @SerializedName("company_logo")
    public String mCompanyLogo;

    @SerializedName("background_color")
    public String mBackgroundColor;

    @SerializedName("company_name")
    public String mCompanyName;

    @SerializedName("payable_amount")
    public String mPayableAmount;

    @SerializedName("bonus_credit_amount")
    public String mBonus;

    @SerializedName("sold_count")
    public int mSoldCount;

    @SerializedName("theme")
    public Theme mTheme;

    @Keep
    public enum Theme {
        @SerializedName("light")
        LIGHT,

        @SerializedName("dark")
        DARK;
    }
}