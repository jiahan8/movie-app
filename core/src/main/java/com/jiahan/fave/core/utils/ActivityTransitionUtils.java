package com.jiahan.fave.core.utils;

import android.content.Intent;
import android.view.View;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

public class ActivityTransitionUtils {
    public static final String EXTRA_CIRCULAR_REVEAL_X = "EXTRA_CIRCULAR_REVEAL_X";
    public static final String EXTRA_CIRCULAR_REVEAL_Y = "EXTRA_CIRCULAR_REVEAL_Y";

    public static void startRevealActivity(View view, Intent startIntent) {
        FragmentActivity activity = (FragmentActivity) view.getContext();
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int revealX = (location[0] + view.getWidth() / 2);
        int revealY = (location[1] + view.getHeight() / 2);
        startIntent.putExtra(EXTRA_CIRCULAR_REVEAL_X, revealX);
        startIntent.putExtra(EXTRA_CIRCULAR_REVEAL_Y, revealY);
        ActivityCompat.startActivity(activity, startIntent, null);
//        activity.overridePendingTransition(0, 0);
    }
}