package com.jiahan.fave.core.data.location;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SettingsClient;
import com.jiahan.fave.core.utils.LocationUtil;
import com.jiahan.fave.core.utils.helper.PermissionHelper;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;

@SuppressLint("MissingPermission")
public class GMSLocationClientAPI implements LocationClientAPI {
    private final Object          LOCK = new Object();
    private final Context         mContext;
    private final LocationRequest mLocationRequest;

    private FusedLocationProviderClient mProviderClient;

    public GMSLocationClientAPI(final Context context) {
        mContext = context.getApplicationContext();
        mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(TimeUnit.SECONDS.toMillis(INTERVAL_IN_SECONDS));
        mLocationRequest.setFastestInterval(TimeUnit.SECONDS.toMillis(FASTEST_INTERVAL_IN_SECONDS));
        mLocationRequest.setNumUpdates(1);
    }

    @Override
    public boolean initialize() {
        if (mProviderClient != null) {
            return true;
        }
        if (!PermissionHelper.isPermissionGranted(mContext, Manifest.permission.ACCESS_FINE_LOCATION)) {
            return false;
        }
        synchronized (LOCK) {
            if (mProviderClient == null) {
                mProviderClient = LocationServices.getFusedLocationProviderClient(mContext);
            }
        }
        return true;
    }

    @Override
    public Observable<Location> getLastKnownLocation() {
        if (!initialize() || !LocationUtil.isGpsEnabled(mContext)) {
            return Observable.error(new LocationUnavailableException());
        }
        return Observable.create((ObservableOnSubscribe<Location>) subscriber ->
                mProviderClient.getLastLocation()
                        .addOnSuccessListener(location -> {
                            if (subscriber.isDisposed()) {
                                return;
                            }
                            if (location == null) {
                                subscriber.onError(new LocationUnavailableException());
                                return;
                            }
                            subscriber.onNext(location);
                            subscriber.onComplete();
                        }))
                .timeout(DEFAULT_TIME_OUT_IN_SECONDS, TimeUnit.SECONDS, Observable.error(new LocationUnavailableException()));
    }

    @Override
    public Observable<Location> getLocationUpdates() {
        if (!initialize()) {
            return Observable.error(new LocationUnavailableException());
        }
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        LocationSettingsRequest locationSettingsRequest = builder.build();
        SettingsClient settingsClient = LocationServices.getSettingsClient(mContext);
        settingsClient.checkLocationSettings(locationSettingsRequest);
        return Observable.create((ObservableOnSubscribe<Location>) subscriber ->
                mProviderClient.getCurrentLocation(LocationRequest.PRIORITY_HIGH_ACCURACY, null)
                        .addOnSuccessListener(location -> {
                            if (subscriber.isDisposed()) {
                                return;
                            }
                            if (location == null) {
                                subscriber.onError(new LocationUnavailableException());
                                return;
                            }
                            subscriber.onNext(location);
                            subscriber.onComplete();
                        }))
                .timeout(DEFAULT_TIME_OUT_IN_SECONDS, TimeUnit.SECONDS, Observable.error(new LocationUnavailableException()));

    }
}