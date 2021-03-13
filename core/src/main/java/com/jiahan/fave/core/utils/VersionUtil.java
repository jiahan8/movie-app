package com.jiahan.fave.core.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.jiahan.fave.core.BuildConfig;

public final class VersionUtil {
    private VersionUtil() {
        // Ensure Singleton
    }

    public static String getInternalProductName() {
        switch (BuildConfig.LIBRARY_PACKAGE_NAME) {
            case "com.jiahan.fave.core":
            case "com.jiahan.fave":
            case "com.jiahan.fave.debug":
                return "Fave-Global";
            default:
                return "Unknown";
        }
    }

    /**
     * This is internal version string for purposes such as sending to
     * Analytics providers or HTTP Header
     *
     * @param context
     * @return
     */
    public static String getInternalVersion(@NonNull final Context context) {
        StringBuilder builder = new StringBuilder();
        try {
            final int versionCode = com.jiahan.fave.core.utils.VersionUtil.getVersionCode(context);
            final String versionName = com.jiahan.fave.core.utils.VersionUtil.getVersionName(context);

            if (!TextUtils.isEmpty(versionName)) {
                builder.append("v");
                builder.append(versionName);
                builder.append("-");
                builder.append(versionCode);
            }
        } catch (NameNotFoundException e) {
            Logger.logException(e);
        }

        return builder.toString();
    }

    /**
     * This is for user visible version string. This is for usage in UI only
     *
     * @param context
     * @return
     */
    public static String getVersionForDisplay(final Context context) {
        StringBuilder builder = new StringBuilder();

        if (context == null) {
            return builder.toString();
        }

        try {
            final int versionCode = com.jiahan.fave.core.utils.VersionUtil.getVersionCode(context);
            final String versionName = com.jiahan.fave.core.utils.VersionUtil.getVersionName(context);

            if (!TextUtils.isEmpty(versionName)) {
                builder.append("version ");
                builder.append(versionName);
                builder.append("-");
                builder.append(versionCode);
            }
        } catch (NameNotFoundException e) {
            Logger.logException(e);
        }

        return builder.toString();
    }

    /**
     * This method retrieves version code of the package.
     *
     * @param context Application context
     * @return int
     * @throws NameNotFoundException Name not found exception
     */
    public static int getVersionCode(Context context) throws NameNotFoundException {
        final PackageInfo manager = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        return manager.versionCode;
    }

    /**
     * This method retrieves version name of the package.
     *
     * @param context Application context
     * @return String
     * @throws NameNotFoundException Name not found exception
     */
    public static String getVersionName(Context context) throws NameNotFoundException {
        final PackageInfo manager = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        return manager.versionName;
    }

    // check if app is in debug or release mode
    public static boolean isReleaseMode() {
        return !BuildConfig.DEBUG;
    }
}
