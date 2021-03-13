package com.jiahan.fave.favecomponent.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;
import com.jiahan.fave.core.entity.User;

public class FaveUser extends User implements Parcelable {
    @SerializedName("email")
    private String                     email;
    @SerializedName("profile")
    private Profile                    profile;
    @SerializedName("veritrans_access_token")
    private String                     veritrans_access_token;
    @SerializedName("authentications")
    private UserAuthenticationMethod[] authentications;
    @SerializedName("fave_employee")
    private Boolean                    fave_employee;
    @SerializedName("has_aabig_linked")
    private boolean                    has_aabig_linked;
    @SerializedName("grabpay_connected")
    private boolean                    grabpay_connected;

    public String getEmail() {
        return email;
    }

    public String getVeritrans_access_token() {
        return veritrans_access_token;
    }

    public Profile getProfile() {
        return profile;
    }

    public UserAuthenticationMethod[] getAuthentications() {
        return authentications;
    }

    public Boolean isFave_employee() {
        return fave_employee;
    }

    public boolean getHas_aabig_linked() {
        return has_aabig_linked;
    }

    public boolean getIsGrabpay_connected() {
        return grabpay_connected;
    }

    public String toJsonString() {
        return new Gson().toJson(this, FaveUser.class);
    }

    public static FaveUser fromJsonString(final String json) {
        try {
            return new Gson().fromJson(json, FaveUser.class);
        } catch (final JsonSyntaxException e) {
            return null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel parcel, final int flag) {
        parcel.writeString(toJsonString());
    }


    public static final Creator<FaveUser> CREATOR = new Creator<FaveUser>() {

        @Override
        public FaveUser createFromParcel(final Parcel parcel) {
            return fromJsonString(parcel.readString());
        }

        @Override
        public FaveUser[] newArray(final int i) {
            return new FaveUser[0];
        }
    };
}