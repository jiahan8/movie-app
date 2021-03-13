package com.jiahan.fave.favecomponent.entity;

import androidx.annotation.Keep;
import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Deal extends BaseDeal {
    @SerializedName("gallery_images")
    public List<String> mGalleryImages;

    @SerializedName("favorited")
    public boolean mFavorited;

    @SerializedName("purchase_count")
    public int mPurchaseCount;

    @SerializedName("hotness_label")
    public String mLastBought;

    @SerializedName("information_label")
    @Nullable
    public String mInformationLabel;

    @SerializedName("what_you_get")
    public String mWhatYouGetHtmlSection;

    @SerializedName("fine_print")
    public String mFinePrintHtmlSection;

    @SerializedName("how_to_redeem_url")
    public String mHowToRedeemUrl;

    @SerializedName("redemption_instructions")
    public String mRedemptionInstructionHtmlSection;

    @SerializedName("cancellation_policy")
    public String mCancellationPolicyHtmlSection;

    @SerializedName("share")
    public Share mShare;

    @SerializedName("reservation")
    public ReservationEntity mReservation;

    @SerializedName("redemption")
    public RedemptionEntity mRedemptionValidity;

    @SerializedName("preferences")
    public List<DealPreference> mDealPreferences;

    @SerializedName("outlet")
    public Outlet mOutlet;

    @SerializedName("purchase_details")
    public PurchaseDetails mPurchaseDetails;

    @SerializedName("total_outlets")
    public int mTotalOutlets;

    @SerializedName("trending")
    public boolean mTrending;

    @SerializedName("flash")
    public boolean mFlash;

    @SerializedName("validity_times")
    public List<ValidityTimes> mValidityTimes;

    public class RedemptionEntity {
        /**
         * redeemable_from : 2018-06-21T00:00:00.000+00:00
         * redeemable_to : 2018-08-20T00:00:00.000+00:00
         */
        @SerializedName("redeemable_from")
        public String mRedeemableFrom;

        @SerializedName("redeemable_to")
        public String mRedeemableTo;
    }

    public class DealPreference {
        @SerializedName("key")
        public DealPreferenceKey mKey;

        @SerializedName("value")
        public Object mValue;
    }

    public class ReservationEntity {
        /**
         * description : Reservation recommended
         * type : recommended
         */
        @SerializedName("description")
        public String mDescription;

        @SerializedName("type")
        public ReservationType mType;
    }

    public class PurchaseDetails {
        @SerializedName("is_available")
        public boolean isAvailable;

        @SerializedName("text")
        public String mPurchaseTitle;
    }

    public class ValidityTimes {
        @SerializedName("day")
        public String mDay;

        @SerializedName("time")
        public String mTime;
    }

    @Keep
    public enum ReservationType {
        @SerializedName("unknown")
        UNKNOWN,

        @SerializedName("not_required")
        NOT_REQUIRED,

        @SerializedName("recommended")
        RECOMMENDED,

        @SerializedName("required")
        REQUIRED;
    }

    @Keep
    public enum DealPreferenceKey {
        @SerializedName("pax")
        PAX,

        @SerializedName("halal")
        HALAL,

        @SerializedName("pork_free")
        PORK_FREE,

        @SerializedName("purchase_cap")
        PURCHASE_CAP,

        @SerializedName("male")
        MALE,

        @SerializedName("female")
        FEMALE
    }
}