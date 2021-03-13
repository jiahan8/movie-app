package com.jiahan.fave.core.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.Uri;

import com.jiahan.fave.core.entity.Coordinates;

public class LocationUtil {

    public static void startDirectionIntent(final Context context, final Double latitude, final Double longitude,
                                            final String locationName) {
        if (context == null) {
            return;
        }
        Uri uri = Uri.parse("geo:" + latitude + "," + longitude + "&q=" + locationName);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(uri);
        context.startActivity(intent);
    }

    public static String toCoordinatesString(final Double latitude, final Double longitude) {
        if (latitude == null || longitude == null) {
            return null;
        }

        return latitude.toString() + "," + longitude;
    }

    public static Coordinates fromCoordinatesString(final String coordinates) {
        Coordinates coordinatesObj = null;
        try {
            if (coordinates != null && coordinates.contains(",")) {
                String[] coordinateArr = coordinates.split(",");
                if (coordinateArr.length == 2) {
                    coordinatesObj = new Coordinates(
                            Double.parseDouble(coordinateArr[0]),
                            Double.parseDouble(coordinateArr[1]));
                }
            }
        } catch (Exception ex) {
            com.jiahan.fave.core.utils.Logger.logException(ex);
        }
        return coordinatesObj;
    }

    public static boolean isGpsEnabled(final Context context) {
        LocationManager service = (LocationManager) context.getSystemService(Activity.LOCATION_SERVICE);
        return service.isProviderEnabled(LocationManager.GPS_PROVIDER) || service.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    public static boolean isLocationPermissionGranted(final Context context) {
        return com.jiahan.fave.core.utils.Utils.isPermissionGranted(context, Manifest.permission.ACCESS_FINE_LOCATION);
    }

    public static boolean isLocationPermissionGrantedAndEnabled(final Context context) {
        return com.jiahan.fave.core.utils.LocationUtil.isGpsEnabled(context) && isLocationPermissionGranted(context);
    }
}