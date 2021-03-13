package com.jiahan.fave.favecomponent.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;
import com.jiahan.fave.core.utils.gson.GsonUtils;

import java.util.List;

public class MainCategory implements Parcelable {
    @SerializedName("id")
    public long mId;

    @SerializedName("title")
    public String mTitle;

    @SerializedName("app_filters")
    public List<AppFilter> mAppFilterList;

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
        return GsonUtils.getGson().toJson(this, MainCategory.class);
    }

    public static MainCategory fromJsonString(final String json) {
        try {
            return GsonUtils.getGson().fromJson(json, MainCategory.class);
        } catch (final JsonSyntaxException e) {
            return null;
        }
    }

    public static final Creator<MainCategory> CREATOR = new Creator<MainCategory>() {

        @Override
        public MainCategory createFromParcel(final Parcel parcel) {
            return fromJsonString(parcel.readString());
        }

        @Override
        public MainCategory[] newArray(final int i) {
            return new MainCategory[0];
        }
    };
}