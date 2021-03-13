package com.jiahan.fave.core.utils.helper;

import android.content.Context;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

public class PermissionHelper {
    private PermissionHelper() {

    }

    public static boolean isPermissionGranted(@NonNull final Context context, @NonNull String... permissions) {
        for (String permission : permissions) {
            try {
                if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            } catch (RuntimeException e) {
                return false;
            }
        }
        return true;
    }
}