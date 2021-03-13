package com.jiahan.fave.core.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;

import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;
import com.jiahan.fave.core.utils.gson.GsonUtils;

public class User implements Parcelable {
    @SerializedName("id")
    public long mId;

    @SerializedName("name")
    @Nullable
    public String mName;

    @SerializedName("jwt")
    @Nullable
    public String mJwt;

    // ----- Boilerplate for Parcelable ----- //
    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(final Parcel parcel) {
            return fromJsonString(parcel.readString());
        }

        @Override
        public User[] newArray(final int i) {
            return new User[0];
        }
    };

    @Override
    public void writeToParcel(final Parcel parcel, final int flag) {
        parcel.writeString(toJsonString());
    }

    public String toJsonString() {
        return GsonUtils.getGson().toJson(this, User.class);
    }

    public static User fromJsonString(final String json) {
        try {
            return GsonUtils.getGson().fromJson(json, User.class);
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