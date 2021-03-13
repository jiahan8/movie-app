package com.jiahan.fave.favecomponent.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;
import com.jiahan.fave.core.utils.gson.GsonUtils;

public class DynamicCashbackInfo implements Parcelable {
    @SerializedName("start_time")
    public String mStartTime;

    @SerializedName("end_time")
    public String mEndTime;

    @SerializedName("dynamic_cashback_rate")
    public int mDynamicCashbackRate;

    @SerializedName("is_now")
    public boolean mIsNow;

    // ----- Boilerplate for Parcelable ----- //
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel parcel, final int flag) {
        parcel.writeString(toJsonString());
    }

    public String toJsonString() {
        return GsonUtils.getGson().toJson(this, DynamicCashbackInfo.class);
    }

    public static DynamicCashbackInfo fromJsonString(final String json) {
        try {
            return GsonUtils.getGson().fromJson(json, DynamicCashbackInfo.class);
        } catch (final JsonSyntaxException e) {
            return null;
        }
    }

    public static final Creator<DynamicCashbackInfo> CREATOR = new Creator<DynamicCashbackInfo>() {
        @Override
        public DynamicCashbackInfo createFromParcel(final Parcel parcel) {
            return fromJsonString(parcel.readString());
        }

        @Override
        public DynamicCashbackInfo[] newArray(final int i) {
            return new DynamicCashbackInfo[0];
        }
    };
}