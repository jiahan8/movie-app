package com.jiahan.fave.favecomponent.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;
import com.jiahan.fave.core.utils.gson.GsonUtils;

public class ECardOnboarding implements Parcelable {
    @SerializedName("title")
    public String mTitle;

    @SerializedName("description")
    public String mDescription;

    @SerializedName("image_url")
    public String mImageUrl;

    // ----- Boilerplate for Parcelable ----- //
    public static final Creator<ECardOnboarding> CREATOR = new Creator<ECardOnboarding>() {

        @Override
        public ECardOnboarding createFromParcel(final Parcel parcel) {
            return fromJsonString(parcel.readString());
        }

        @Override
        public ECardOnboarding[] newArray(final int i) {
            return new ECardOnboarding[0];
        }
    };

    @Override
    public void writeToParcel(final Parcel parcel, final int flag) {
        parcel.writeString(toJsonString());
    }

    public String toJsonString() {
        return GsonUtils.getGson().toJson(this, ECardOnboarding.class);
    }

    public static ECardOnboarding fromJsonString(final String json) {
        try {
            return GsonUtils.getGson().fromJson(json, ECardOnboarding.class);
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