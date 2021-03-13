package com.jiahan.fave.favecomponent.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Keep;

import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;
import com.jiahan.fave.core.utils.gson.GsonUtils;

import java.util.List;

public class Product implements Parcelable {
    @SerializedName("title")
    public String mTitle;

    @SerializedName("type")
    public ProductType mType;

    @SerializedName("app_filters")
    public List<AppFilter> mAppFilterList;

    @Keep
    public enum ProductType {
        @SerializedName("deals")
        DEAL("deals"),

        @SerializedName("outlets")
        OUTLET("outlets"),

        @SerializedName("e_cards")
        ECARD("e_cards"),

        @SerializedName("online")
        ONLINE("online");

        private final String mValue;

        public String getValue() {
            return mValue;
        }

        ProductType(String value) {
            mValue = value;
        }
    }

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
        return GsonUtils.getGson().toJson(this, Product.class);
    }

    public static Product fromJsonString(final String json) {
        try {
            return GsonUtils.getGson().fromJson(json, Product.class);
        } catch (final JsonSyntaxException e) {
            return null;
        }
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {

        @Override
        public Product createFromParcel(final Parcel parcel) {
            return fromJsonString(parcel.readString());
        }

        @Override
        public Product[] newArray(final int i) {
            return new Product[0];
        }
    };
}