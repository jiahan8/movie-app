package com.jiahan.fave.core.utils;

import androidx.annotation.NonNull;

public class StringUtil {
    public static String capitalise(@NonNull final String string) {
        if (string.isEmpty()) {
            return string;
        }
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }

    public static String removeAllNonAlphanumeric(@NonNull final String string) {
        if (string.isEmpty()) {
            return string;
        }
        return string.replaceAll("[^a-zA-Z0-9]", "");
    }
}