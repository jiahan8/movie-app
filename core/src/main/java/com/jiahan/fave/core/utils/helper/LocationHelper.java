package com.jiahan.fave.core.utils.helper;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

import androidx.annotation.NonNull;

import com.google.android.gms.maps.model.LatLng;
import com.jiahan.fave.core.utils.Logger;
import com.jiahan.fave.core.utils.MathHelper;

import java.io.Serializable;
import java.util.Comparator;

public class LocationHelper {
    private LocationHelper() {

    }

    public static boolean isGpsEnabled(@NonNull final Context context) {
        LocationManager service = (LocationManager) context.getSystemService(Activity.LOCATION_SERVICE);
        assert service != null;
        return service.isProviderEnabled(LocationManager.GPS_PROVIDER) || service.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    public static String toLatLngString(@NonNull final Double latitude, @NonNull final Double longitude) {
        return latitude.toString() + "," + longitude;
    }

    public static LatLng toLatlng(@NonNull final String coordinates) {
        LatLng latLng = null;
        try {
            if (coordinates.contains(",")) {
                String[] coordinateSplitter = coordinates.split(",");
                if (coordinateSplitter.length == 2) {
                    latLng = new LatLng(Double.parseDouble(coordinateSplitter[0]), Double.parseDouble(coordinateSplitter[1]));
                }
            }
        } catch (Exception ex) {
            Logger.logException(ex);
        }
        return latLng;
    }

    public static double straightLineDistanceFromLatLngs(double originLatitude, double originLongitude,
                                                         double destLatitude, double destLongitude) {
        final double degreesToKilometers = (Math.PI * 6371) / 180;
        final double diffLatitude = originLatitude - destLatitude;
        final double diffLongitude = originLongitude - destLongitude;
        return degreesToKilometers * (Math.sqrt((diffLatitude * diffLatitude) + (diffLongitude * diffLongitude)));
    }

    public static final class LocationFreshnessComparator implements Comparator<Location>, Serializable {
        private static final long serialVersionUID = 4789989293273441714L;

        @Override
        public int compare(final Location lhs, final Location rhs) {
            long lhsTime;
            long rhsTime;
            lhsTime = lhs.getElapsedRealtimeNanos();
            rhsTime = rhs.getElapsedRealtimeNanos();
            return MathHelper.compare(lhsTime, rhsTime);
        }
    }
}