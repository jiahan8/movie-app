package com.jiahan.fave.core.network;

import android.content.Context;
import android.net.ConnectivityManager;

import com.jiahan.fave.core.common.CoreApplication;

public class NetworkUtils {
    public static boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) CoreApplication.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}