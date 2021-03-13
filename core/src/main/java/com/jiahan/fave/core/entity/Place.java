package com.jiahan.fave.core.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;

import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;
import com.jiahan.fave.core.utils.gson.GsonUtils;

public class Place implements Parcelable {
    @SerializedName("name")
    public String mName;

    @SerializedName("description")
    @Nullable
    public String mAddress;

    @SerializedName("latitude")
    public double mLatitude;

    @SerializedName("longitude")
    public double mLongitude;

    // ----- Boilerplate for Parcelable ----- //
    public static final Creator<Place> CREATOR = new Creator<Place>() {
        @Override
        public Place createFromParcel(final Parcel parcel) {
            return fromJsonString(parcel.readString());
        }

        @Override
        public Place[] newArray(final int i) {
            return new Place[0];
        }
    };

    @Override
    public void writeToParcel(final Parcel parcel, final int flag) {
        parcel.writeString(toJsonString());
    }

    public String toJsonString() {
        return GsonUtils.getGson().toJson(this, Place.class);
    }

    public static Place fromJsonString(final String json) {
        try {
            return GsonUtils.getGson().fromJson(json, Place.class);
        } catch (final JsonSyntaxException e) {
            return null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }
    // ----- End Boilerplate for Parcelable ----- //
}