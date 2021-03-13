package com.jiahan.fave.favecomponent.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Filter {
    @SerializedName("title")
    public String mTitle;

    @SerializedName("key")
    public String mKey;

    @SerializedName("data")
    public List<FilterData> mData;

    @SerializedName("default")
    public String mDefault;

    @SerializedName("max_default")
    public String mMaxDefault;

    @SerializedName("min_default")
    public String mMinDefault;

    public static class FilterKey {
        public static String SORT_BY         = "sort_by";
        public static String OPENING_HOURS   = "opening_hours";
        public static String REDEEMABLE      = "redeemable";
        public static String MIN_PRICE_RANGE = "min_price_range";
        public static String MAX_PRICE_RANGE = "max_price_range";
        public static String MIN_DISTANCE    = "min_distance";
        public static String MAX_DISTANCE    = "max_distance";
        public static String LABELS          = "labels";
        public static String RATING          = "rating";
        public static String PRICE_RANGE     = "price_range";
        public static String DISTANCE        = "distance";
    }
}