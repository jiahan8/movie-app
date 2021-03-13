package com.jiahan.fave.core.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;

import okhttp3.HttpUrl;

public class GrabDeeplinkUtils {
    private static final String SOURCE_ID       = "byYQTxMit2";
    private static final String SOURCE_APP_NAME = "Fave";

    private static boolean isGrabAppInstalled(final Context context) {
        if (context == null) {
            return false;
        }

        try {
            final PackageInfo info = context.getPackageManager().getPackageInfo("com.grabtaxi.passenger", PackageManager.GET_ACTIVITIES);
            return info != null;
        } catch (Exception ignored) {
            return false;
        }
    }

    private static String getGrabDeeplink(final String dropOffKeyword,
                                          final String dropOffAddress,
                                          final double latitude,
                                          final double longitude) {
        if (TextUtils.isEmpty(dropOffKeyword) || TextUtils.isEmpty(dropOffAddress)) {
            return null;
        }

        final Uri.Builder builder = Uri.parse("grab://open").buildUpon();
        builder.appendQueryParameter("sourceID", SOURCE_ID);
        builder.appendQueryParameter("sourceAppName", SOURCE_APP_NAME);
        builder.appendQueryParameter("screenType", "BOOKING");
        builder.appendQueryParameter("dropOffKeywords", dropOffKeyword);
        builder.appendQueryParameter("dropOffAddress", dropOffAddress);
        builder.appendQueryParameter("dropOffLatitude", String.valueOf(latitude));
        builder.appendQueryParameter("dropOffLongitude", String.valueOf(longitude));
        return builder.build().toString();
    }

    public static Intent getGrabIntent(final Context context,
                                       final String dropOffKeyword,
                                       final String dropOffAddress,
                                       final double latitude,
                                       final double longitude) {
        if (context == null) {
            return null;
        }

        final String grabDeeplink = com.jiahan.fave.core.utils.GrabDeeplinkUtils.getGrabDeeplink(dropOffKeyword, dropOffAddress, latitude, longitude);
        if (TextUtils.isEmpty(grabDeeplink)) {
            return null;
        }

        if (!com.jiahan.fave.core.utils.GrabDeeplinkUtils.isGrabAppInstalled(context)) {
            final HttpUrl.Builder builder = HttpUrl.parse("https://grabtaxi.onelink.me/2695613898").newBuilder();
            builder.setQueryParameter("pid", SOURCE_ID);
            builder.setQueryParameter("af_dp", grabDeeplink);
            final String newUserUrl = builder.build().url().toString();

            final Intent newUserIntent = new Intent(Intent.ACTION_VIEW);
            newUserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            newUserIntent.setData(Uri.parse(newUserUrl));
            return newUserIntent;
        }

        final Intent existingUserIntent = new Intent(Intent.ACTION_VIEW);
        existingUserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        existingUserIntent.setData(Uri.parse(grabDeeplink));
        return existingUserIntent;
    }
}
