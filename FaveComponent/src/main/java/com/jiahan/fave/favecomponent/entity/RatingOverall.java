package com.jiahan.fave.favecomponent.entity;

import com.google.gson.annotations.SerializedName;

public class RatingOverall {
    @SerializedName("value_for_money")
    public int mValueForMoney;

    @SerializedName("ambience")
    public int mAmbience;

    @SerializedName("service")
    public int mService;

    @SerializedName("food")
    public int mFood;

    @SerializedName("total_count")
    public int mTotalCount;

    @SerializedName("overall")
    public double mOverall;
}