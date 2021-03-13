package com.jiahan.fave.core.tracker;

import android.content.Context;

import com.clevertap.android.sdk.CleverTapAPI;
import com.jiahan.fave.core.utils.Logger;

public class CleverTap {
    private CleverTapAPI mCleverTapAPI;

    public CleverTap(final Context context) {
        try {
            mCleverTapAPI = CleverTapAPI.getDefaultInstance(context);
        } catch (Exception ex) {
            Logger.logException(ex);
        }
        CleverTapAPI.setDebugLevel(CleverTapAPI.LogLevel.DEBUG);
    }

    public void pushEvent(EventActions eventActions) {
        if (mCleverTapAPI == null) {
            Logger.log("Clevertap initialize failed");
            return;
        }
        mCleverTapAPI.pushEvent(eventActions.getEventName(), eventActions.getProperties());
    }
}