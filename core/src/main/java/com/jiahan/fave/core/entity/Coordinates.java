package com.jiahan.fave.core.entity;

public class Coordinates {
    private final double mLatitude;
    private final double mLongitude;

    public Coordinates(final double latitude, final double longitude) {
        mLatitude = latitude;
        mLongitude = longitude;
    }

    public double getLatitude() {
        return mLatitude;
    }

    public double getLongitude() {
        return mLongitude;
    }
}