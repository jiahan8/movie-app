package com.jiahan.fave.favecomponent.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;
import com.jiahan.fave.core.utils.gson.GsonUtils;

import java.util.List;

public class OutletBusinessHour implements Parcelable {
    @SerializedName("hours")
    public List<String> mHours;

    @SerializedName("day")
    public String mDay;

    @SerializedName("open")
    public boolean      mIsOpen;

    @SerializedName("is_today")
    public boolean mIsToday;

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
        return GsonUtils.getGson().toJson(this, OutletBusinessHour.class);
    }

    public static OutletBusinessHour fromJsonString(final String json) {
        try {
            return GsonUtils.getGson().fromJson(json, OutletBusinessHour.class);
        } catch (final JsonSyntaxException e) {
            return null;
        }
    }

    public static final Creator<OutletBusinessHour> CREATOR = new Creator<OutletBusinessHour>() {

        @Override
        public OutletBusinessHour createFromParcel(final Parcel parcel) {
            return fromJsonString(parcel.readString());
        }

        @Override
        public OutletBusinessHour[] newArray(final int i) {
            return new OutletBusinessHour[0];
        }
    };
}