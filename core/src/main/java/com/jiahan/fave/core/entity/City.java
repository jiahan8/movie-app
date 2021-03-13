package com.jiahan.fave.core.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;
import com.jiahan.fave.core.utils.gson.GsonUtils;

public class City implements Parcelable {
    @SerializedName("id")
    public long id;

    @SerializedName("name")
    public String name;

    @SerializedName("address")
    public String mAddress;

    @SerializedName("slug")
    public String slug;

    @SerializedName("coordinates")
    public String coordinates;

    @SerializedName("timezone")
    public String timezone;

    @SerializedName("currency")
    public String currency;

    @SerializedName("currency_display")
    public String currency_display;

    @SerializedName("country_code")
    public String country_code;

    @SerializedName("currency_exponent")
    public int currency_exponent;

    public City(){}

    public City(final long id, final String name, final String slug, final String coordinates, final String timezone, final String currency, final String currency_display, final String country_code, final int currency_exponent) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.coordinates = coordinates;
        this.timezone = timezone;
        this.currency = currency;
        this.currency_display = currency_display;
        this.country_code = country_code;
        this.currency_exponent = currency_exponent;
    }


    // ----- Boilerplate for Parcelable ----- //
    public static final Creator<City> CREATOR = new Creator<City>() {
        @Override
        public City createFromParcel(final Parcel parcel) {
            return fromJsonString(parcel.readString());
        }

        @Override
        public City[] newArray(final int i) {
            return new City[0];
        }
    };

    @Override
    public void writeToParcel(final Parcel parcel, final int flag) {
        parcel.writeString(toJsonString());
    }

    public String toJsonString() {
        return GsonUtils.getGson().toJson(this, City.class);
    }

    public static City fromJsonString(final String json) {
        try {
            return GsonUtils.getGson().fromJson(json, City.class);
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