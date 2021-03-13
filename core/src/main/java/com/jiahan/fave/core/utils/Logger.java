package com.jiahan.fave.core.utils;

import android.util.Log;

import androidx.annotation.NonNull;

import com.crashlytics.android.Crashlytics;

/**
 * Util class that wraps around the standard Android Logger.
 * This allows use to protect the logging based on DEBUG or Production APK versions.
 * <p>
 * In DEBUG Build Logs go to ADB Logcat
 * in Production Build Logs go to Crashytics and Firebase Crash
 */
public final class Logger {
    private static final String  TAG   = com.jiahan.fave.core.utils.Logger.class.getName();
    private static final boolean DEBUG = !com.jiahan.fave.core.utils.VersionUtil.isReleaseMode();

    private static final int LOG_LEVEL_DEBUG = 1;
    private static final int LOG_LEVEL_WARN  = 2;

    private static final int PROD_BUILD_LOG_LEVEL  = LOG_LEVEL_WARN;
    private static final int DEBUG_BUILD_LOG_LEVEL = LOG_LEVEL_DEBUG;

    private Logger() {
        // enforcing singleton
        super();
    }

    private static int getCurrentLogLevel() {
        return com.jiahan.fave.core.utils.Logger.DEBUG ? DEBUG_BUILD_LOG_LEVEL : PROD_BUILD_LOG_LEVEL;
    }

    public static void log(@NonNull String message) {
        if (LOG_LEVEL_DEBUG < getCurrentLogLevel()) {
            return;
        }
        if (com.jiahan.fave.core.utils.Logger.DEBUG) {
            Log.d(TAG, "" + message);
            return;
        }
        Crashlytics.log(message);
    }

    public static void logException(final Throwable throwable) {
        if (com.jiahan.fave.core.utils.Logger.DEBUG) {
            throwable.printStackTrace();
            return;
        }
        Crashlytics.logException(throwable);
    }
}