package com.jiahan.fave.core.utils.drawableUtils;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

import androidx.annotation.DimenRes;
import androidx.core.content.ContextCompat;

import com.jiahan.fave.core.R;
import com.jiahan.fave.core.common.CoreApplication;

public class WidgetDrawable {
    public static Drawable getPinkBorderBackground() {
        return DrawableUtils.createDrawable(
                GradientDrawable.RECTANGLE,
                R.dimen.size_12,
                R.dimen.size_2,
                R.color.lipstick,
                R.color.white);
    }
    public static Drawable getPinkBackground() {
        return DrawableUtils.createDrawable(
                GradientDrawable.RECTANGLE,
                R.dimen.size_12,
                0,
                0,
                R.color.lipstick);
    }

    public static Drawable getDefaultButtonBackground() {
        return getDefaultButtonBackground(R.dimen.size_16);
    }

    public static Drawable getDefaultButtonBackground(@DimenRes int cornerRadius) {
        return DrawableUtils.createGradientDrawable(
                GradientDrawable.Orientation.TL_BR,
                GradientDrawable.LINEAR_GRADIENT,
                cornerRadius,
                ContextCompat.getColor(CoreApplication.getInstance(), R.color.lipstick),
                ContextCompat.getColor(CoreApplication.getInstance(), R.color.lipstick),
                ContextCompat.getColor(CoreApplication.getInstance(), R.color.grapefruit));
    }

    public static Drawable getDisabledButtonBackground() {
        return getDisabledButtonBackground(R.dimen.size_16);
    }

    public static Drawable getDisabledButtonBackground(@DimenRes int cornerRadius) {
        return DrawableUtils.createGradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                GradientDrawable.LINEAR_GRADIENT,
                cornerRadius,
                ContextCompat.getColor(CoreApplication.getInstance(), R.color.very_light_pink_seven),
                ContextCompat.getColor(CoreApplication.getInstance(), R.color.very_light_pink_eight));
    }
}