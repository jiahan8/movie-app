package com.jiahan.fave.core.data.location;

import android.location.Location;

import io.reactivex.rxjava3.core.Observable;


public interface LocationClientAPI {
    int DEFAULT_TIME_OUT_IN_SECONDS = 10;
    int INTERVAL_IN_SECONDS         = 10;
    int FASTEST_INTERVAL_IN_SECONDS = 2;

    boolean initialize();

    Observable<Location> getLastKnownLocation();

    Observable<Location> getLocationUpdates();
}