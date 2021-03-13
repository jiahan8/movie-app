package com.jiahan.fave.favecomponent.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;
import com.jiahan.fave.core.utils.gson.GsonUtils;

public class AppFilter implements Parcelable {
    @SerializedName("id")
    public Long mId;

    @SerializedName("name")
    public String mTitle;

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
        return GsonUtils.getGson().toJson(this, AppFilter.class);
    }

    public static AppFilter fromJsonString(final String json) {
        try {
            return GsonUtils.getGson().fromJson(json, AppFilter.class);
        } catch (final JsonSyntaxException e) {
            return null;
        }
    }

    public static final Creator<AppFilter> CREATOR = new Creator<AppFilter>() {

        @Override
        public AppFilter createFromParcel(final Parcel parcel) {
            return fromJsonString(parcel.readString());
        }

        @Override
        public AppFilter[] newArray(final int i) {
            return new AppFilter[0];
        }
    };
}