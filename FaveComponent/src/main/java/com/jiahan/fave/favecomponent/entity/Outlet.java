package com.jiahan.fave.favecomponent.entity;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class Outlet extends BaseOutlet {
    @SerializedName("favepay_count")
    public long mFavepayCount;

    @SerializedName("latitude")
    public double mLatitude;

    @SerializedName("longitude")
    public double mLongitude;

    @SerializedName("telephone")
    public String mTelephone;

    @SerializedName("cashback_info")
    public CashbackInfo mCashbackInfo;

    @SerializedName("company")
    public Company mCompany;

    @SerializedName("outlet_timing")
    public OutletTiming mOutletTiming;

    @SerializedName("deals_count")
    public int mDealsCount;

    @SerializedName("announcement")
    public OutletAnnouncement mOutletAnnouncement;

    @SerializedName("share_url_details")
    public Share mShare;

    @SerializedName("has_dynamic_cashback")
    public boolean mHasDynamicCashback;

    @SerializedName("cashback_has_tnc")
    public boolean mHasNormalCashbackTnc;

    @SerializedName("dynamic_cashback_info")
    @Nullable
    public DynamicCashbackInfo mDynamicCashbackInfo;

    public class OutletAnnouncement {
        @SerializedName("title")
        public String mTitle;

        @SerializedName("announcement_text")
        public String mContent;

        @SerializedName("image")
        public String mImage;
    }
}