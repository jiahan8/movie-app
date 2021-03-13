package com.jiahan.fave.core.data.location;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;

import com.jiahan.fave.core.utils.helper.LocationHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;

@SuppressLint("MissingPermission")
public class DefaultLocationClientAPI implements LocationClientAPI {
    private static final int DEFAULT_TIME_OUT_IN_SECONDS = 5;

    private final Object LOCK = new Object();

    private Context         mContext;
    private LocationManager mLocationManager;

    public DefaultLocationClientAPI(final Context context) {
        mContext = context.getApplicationContext();
    }

    @Override
    public boolean initialize() {
        if (mLocationManager != null) {
            return true;
        }
        if (!LocationHelper.isGpsEnabled(mContext)) {
            return false;
        }
        synchronized (LOCK) {
            if (mLocationManager == null) {
                mLocationManager = (LocationManager) this.mContext.getSystemService(Context.LOCATION_SERVICE);
            }
        }
        return mLocationManager != null;
    }

    @Override
    public Observable<Location> getLastKnownLocation() {
        if (!initialize() && !LocationHelper.isGpsEnabled(mContext)) {
            return Observable.error(new LocationUnavailableException());
        }
        final List<String> providers = mLocationManager.getAllProviders();
        if (providers.isEmpty()) {
            return Observable.error(new LocationUnavailableException());
        }
        final List<Location> locations = new ArrayList<>();
        for (final String provider : providers) {
            try {
                final Location currProviderLocation = mLocationManager.getLastKnownLocation(provider);
                if (currProviderLocation == null) {
                    continue;
                }
                locations.add(currProviderLocation);
            } catch (SecurityException e) {
                return Observable.error(new LocationUnavailableException());
            }
        }
        if (locations.size() == 0) {
            return Observable.error(new LocationUnavailableException());
        }
        Collections.sort(locations, new LocationHelper.LocationFreshnessComparator());
        return Observable.just(locations.get(locations.size() - 1));
    }

    @Override
    public Observable<Location> getLocationUpdates() {
        if (!initialize() && !LocationHelper.isGpsEnabled(mContext)) {
            return Observable.error(new LocationUnavailableException());
        }
        final Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);
        criteria.setPowerRequirement(Criteria.POWER_MEDIUM);
        return Observable.create((ObservableOnSubscribe<Location>) emitter -> mLocationManager.requestSingleUpdate(criteria, new LocationListener() {
                    @Override
                    public void onLocationChanged(final Location location) {
                        emitter.onNext(location);
                        emitter.onComplete();
                    }

                    @Override
                    public void onStatusChanged(final String provider, final int status, final Bundle extras) {
                        // Nothing to do
                    }

                    @Override
                    public void onProviderEnabled(final String provider) {
                        // Nothing to do
                    }

                    @Override
                    public void onProviderDisabled(final String provider) {
                        // Nothing to do
                    }
                }
                , Looper.getMainLooper()))
                .
                        timeout(DEFAULT_TIME_OUT_IN_SECONDS, TimeUnit.SECONDS, Observable.error(new LocationUnavailableException()));
    }
}