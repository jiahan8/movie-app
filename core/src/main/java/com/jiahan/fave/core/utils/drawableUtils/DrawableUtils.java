package com.jiahan.fave.core.utils.drawableUtils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.Dimension;
import androidx.annotation.DrawableRes;
import androidx.core.content.ContextCompat;

import com.jiahan.fave.core.R;
import com.jiahan.fave.core.common.CoreApplication;

/**
 * Created by zarea at 2020
 */
public class DrawableUtils {
    /**
     * @param orientation  eg: GradientDrawable.Orientation.TOP_BOTTOM
     * @param gradientType eg: GradientDrawable.LINEAR_GRADIENT
     * @param cornerRadius Do not pass value less than 1 because only accept integer
     * @param colors       Color from startFromBoost to end
     * @return Normal Drawable
     */
    public static GradientDrawable createGradientDrawable(GradientDrawable.Orientation orientation,
                                                          int gradientType,
                                                          @DimenRes int cornerRadius,
                                                          int... colors) {
        final Context context = CoreApplication.getInstance().getApplicationContext();
        if (colors.length == 1) {
            return createDrawable(
                    GradientDrawable.RECTANGLE,
                    cornerRadius,
                    0,
                    0,
                    colors[0]);
        }
        GradientDrawable gradientDrawable = new GradientDrawable(orientation, colors);
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        gradientDrawable.setGradientType(gradientType);
        if (cornerRadius != 0) {
            gradientDrawable.setCornerRadius((int) context.getResources().getDimension(cornerRadius));
        }
        return gradientDrawable;
    }

    public static GradientDrawable createGradientRadialDrawable(GradientDrawable.Orientation orientation,
                                                                int gradientType,
                                                                @DimenRes int cornerRadius,
                                                                int gradientRadius,
                                                                float gradientCenterX,
                                                                float gradientCenterY,
                                                                int... colors) {
        GradientDrawable drawable = createGradientDrawable(orientation, gradientType, cornerRadius, colors);
        drawable.setGradientRadius(gradientRadius);
        drawable.setGradientCenter(gradientCenterX, gradientCenterY);
        return drawable;
    }

    /**
     * @param shape           eg: GradientDrawable.RECTANGLE
     * @param cornerRadius    Do not pass value less than 1 because only accept integer
     * @param borderSize      Do not pass value less than 1 because only accept integer
     * @param borderColor     Border Color
     * @param backgroundColor Solid color
     * @return Normal Drawable
     */
    public static GradientDrawable createDrawable(int shape,
                                                  @DimenRes int cornerRadius,
                                                  @DimenRes int borderSize,
                                                  @ColorRes int borderColor,
                                                  int backgroundColor) {
        final Context context = CoreApplication.getInstance().getApplicationContext();
        GradientDrawable shapeDrawable = new GradientDrawable();
        shapeDrawable.setShape(shape);
        if (cornerRadius != 0) {
            shapeDrawable.setCornerRadius(context.getResources().getDimension(cornerRadius));
        }
        if (borderSize != 0 && borderColor != 0) {
            shapeDrawable.setStroke((int) context.getResources().getDimension(borderSize),
                    ContextCompat.getColor(context, borderColor));
        }
        if (backgroundColor > 0) {
            shapeDrawable.setColor(ContextCompat.getColor(context, backgroundColor));
        }
        else {
            shapeDrawable.setColor(backgroundColor);
        }
        return shapeDrawable;
    }

    /**
     * @param shape                   eg: GradientDrawable.RECTANGLE
     * @param cornerRadiusTopLeft     Do not pass value less than 1 because only accept integer
     * @param cornerRadiusTopRight    Do not pass value less than 1 because only accept integer
     * @param cornerRadiusBottomLeft  Do not pass value less than 1 because only accept integer
     * @param cornerRadiusBottomRight Do not pass value less than 1 because only accept integer
     * @param borderSize              Do not pass value less than 1 because only accept integer
     * @param borderColor             Border Color
     * @param backgroundColor         Solid color
     * @return Normal Drawable
     */
    public static GradientDrawable createDrawable(int shape,
                                                  @DimenRes int cornerRadiusTopLeft,
                                                  @DimenRes int cornerRadiusTopRight,
                                                  @DimenRes int cornerRadiusBottomLeft,
                                                  @DimenRes int cornerRadiusBottomRight,
                                                  @DimenRes int borderSize,
                                                  @ColorRes int borderColor,
                                                  int backgroundColor) {
        final Context context = CoreApplication.getInstance().getApplicationContext();
        float topLeft = cornerRadiusTopLeft != 0 ? context.getResources().getDimension(cornerRadiusTopLeft) : 0;
        float topRight = cornerRadiusTopRight != 0 ? context.getResources().getDimension(cornerRadiusTopRight) : 0;
        float bottomLeft = cornerRadiusBottomLeft != 0 ? context.getResources().getDimension(cornerRadiusBottomLeft) : 0;
        float bottomRight = cornerRadiusBottomRight != 0 ? context.getResources().getDimension(cornerRadiusBottomRight) : 0;
        GradientDrawable shapeDrawable = new GradientDrawable();
        shapeDrawable.setShape(shape);
        shapeDrawable.setCornerRadii(new float[]{topLeft, topLeft, topRight, topRight, bottomRight, bottomRight, bottomLeft, bottomLeft});
        if (borderSize != 0 && borderColor != 0) {
            shapeDrawable.setStroke((int) context.getResources().getDimension(borderSize),
                    ContextCompat.getColor(context, borderColor));
        }
        if (backgroundColor > 0) {
            shapeDrawable.setColor(ContextCompat.getColor(context, backgroundColor));
        }
        else {
            shapeDrawable.setColor(backgroundColor);
        }
        return shapeDrawable;
    }

    /**
     * @param shape           eg: GradientDrawable.RECTANGLE
     * @param cornerRadius    Do not pass value less than 1 because only accept integer
     * @param borderSize      Do not pass value less than 1 because only accept integer
     * @param borderColor     Border Color
     * @param backgroundColor Solid color
     * @return Normal Drawable using pixel value
     */
    public static GradientDrawable createDrawablePixel(int shape,
                                                       @Dimension int cornerRadius,
                                                       @Dimension int borderSize,
                                                       @ColorInt int borderColor,
                                                       @ColorInt int backgroundColor) {
        GradientDrawable shapeDrawable = new GradientDrawable();
        shapeDrawable.setShape(shape);
        if (cornerRadius != 0) {
            shapeDrawable.setCornerRadius(cornerRadius);
        }
        if (borderSize != 0 && borderColor != 0) {
            shapeDrawable.setStroke(borderSize,
                    borderColor);
        }
        if (backgroundColor != 0) {
            shapeDrawable.setColor(backgroundColor);
        }
        return shapeDrawable;
    }

    /**
     * @param drawable        Drawable to be update, purpose to get the border size only
     * @param shape           eg: GradientDrawable.RECTANGLE
     * @param cornerRadius    Do not pass value less than 1 because only accept integer
     * @param backgroundColor Solid color
     * @return Normal Drawable
     */
    public static GradientDrawable updateDrawable(@DrawableRes int drawable,
                                                  int shape,
                                                  @DimenRes int cornerRadius,
                                                  @ColorRes int backgroundColor) {
        final Context context = CoreApplication.getInstance().getApplicationContext();
        GradientDrawable shapeDrawable = (GradientDrawable) ContextCompat.getDrawable(context, drawable).mutate();
        shapeDrawable.setShape(shape);
        if (cornerRadius != 0) {
            shapeDrawable.setCornerRadius(context.getResources().getDimension(cornerRadius));
        }
        shapeDrawable.setColor(ContextCompat.getColor(context, backgroundColor));
        return shapeDrawable;
    }

    /**
     * @param isEnableDisable To determine this is for enable-disable selector
     * @param normalResId     Either normal state or enable state
     * @param selectedResId   Either selected state or disable state
     * @return StateListDrawable
     */
    public static StateListDrawable createColorSelector(boolean isEnableDisable,
                                                        @ColorRes int normalResId,
                                                        @ColorRes int selectedResId) {
        final Context context = CoreApplication.getInstance().getApplicationContext();
        int normal = ContextCompat.getColor(context, normalResId);
        int selected = ContextCompat.getColor(context, selectedResId);
        StateListDrawable stateListDrawable = new StateListDrawable();
        if (isEnableDisable) {
            stateListDrawable.addState(new int[]{android.R.attr.state_enabled}, new ColorDrawable(normal));
            stateListDrawable.addState(new int[]{-android.R.attr.state_enabled}, new ColorDrawable(selected));
        }
        else {
            stateListDrawable.addState(new int[]{android.R.attr.state_selected}, new ColorDrawable(selected));
            stateListDrawable.addState(new int[]{}, new ColorDrawable(normal));
        }
        return stateListDrawable;
    }

    /**
     * @param isForCheckedState To determine this is for enable-disable selector
     * @param normal            Either normal state or unchecked state
     * @param selected          Either selected state or checked state
     * @return StateListDrawable
     */
    public static StateListDrawable createDrawableSelector(boolean isForCheckedState,
                                                           Drawable normal,
                                                           Drawable selected) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        if (isForCheckedState) {
            stateListDrawable.addState(new int[]{android.R.attr.state_enabled}, normal);
            stateListDrawable.addState(new int[]{-android.R.attr.state_enabled}, selected);
        }
        else {
            stateListDrawable.addState(new int[]{android.R.attr.state_selected}, selected);
            stateListDrawable.addState(new int[]{android.R.attr.state_checked}, selected);
            stateListDrawable.addState(new int[]{}, normal);
        }
        return stateListDrawable;
    }

    /**
     * @param normal  Normal state
     * @param pressed Pressed state
     * @return StateListDrawable
     */
    public static StateListDrawable createDrawableSelector(Drawable normal,
                                                           Drawable pressed) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_selected}, pressed);
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, pressed);
        stateListDrawable.addState(new int[]{}, normal);
        return stateListDrawable;
    }

    /**
     * @param isEnableDisable To determine this is for enable-disable selector
     * @param normalResId     Either normal state or enable state
     * @param selectedResId   Either selected state or disable state
     * @return ColorStateList
     */
    public static ColorStateList createTextColorSelector(boolean isEnableDisable,
                                                         @ColorRes int normalResId,
                                                         @ColorRes int selectedResId) {
        final Context context = CoreApplication.getInstance().getApplicationContext();
        int normal = ContextCompat.getColor(context, normalResId);
        int selected = ContextCompat.getColor(context, selectedResId);
        int[][] states;
        int[] colors;
        if (isEnableDisable) {
            states = new int[][]{
                    new int[]{-android.R.attr.state_enabled},
                    new int[]{android.R.attr.state_enabled},
            };
            colors = new int[]{
                    selected,
                    normal
            };
        }
        else {
            states = new int[][]{
                    new int[]{android.R.attr.state_selected},
                    new int[]{android.R.attr.state_checked},
                    new int[]{}
            };
            colors = new int[]{
                    selected,
                    selected,
                    normal
            };
        }
        return new ColorStateList(states, colors);
    }

    /**
     * @param normalResId  Normal State
     * @param pressedResId Pressed state
     * @return ColorStateList
     */
    public static ColorStateList createTextColorSelector(@ColorRes int normalResId,
                                                         @ColorRes int pressedResId) {
        final Context context = CoreApplication.getInstance().getApplicationContext();
        int normal = ContextCompat.getColor(context, normalResId);
        int pressed = ContextCompat.getColor(context, pressedResId);
        int[][] states;
        int[] colors;
        states = new int[][]{
                new int[]{android.R.attr.state_pressed},
                new int[]{android.R.attr.state_selected},
                new int[]{}
        };
        colors = new int[]{
                pressed,
                pressed,
                normal
        };
        return new ColorStateList(states, colors);
    }

    /**
     * Use createRippleDrawable(Drawable background) instead
     */
    @Deprecated
    public static Drawable createRippleDrawable(Context context, Drawable background) {
        //FIXME remove this function
        return createRippleDrawable(background);
    }

    /**
     * @param background background for normal view
     * @return RippleDrawable
     */
    public static Drawable createRippleDrawable(Drawable background) {
        final Context context = CoreApplication.getInstance().getApplicationContext();
        int rippleColor = ContextCompat.getColor(context, R.color.color_control_highlight);
        return new RippleDrawable(
                ColorStateList.valueOf(rippleColor),
                background,
                background);
    }

    /**
     * @param drawables A list of drawables to be stack on
     * @return LayerDrawable a.k.a layerlist
     */
    public static LayerDrawable createLayerDrawable(Drawable... drawables) {
        return new LayerDrawable(drawables);
    }

    public static Drawable getTintedDrawable(Context context, @DrawableRes int drawableResId, @ColorRes int colorResId) {
        Drawable drawable = ContextCompat.getDrawable(context, drawableResId);
        return getTintedDrawable(context, drawable, colorResId);
    }

    public static Drawable getTintedDrawable(Context context, Drawable drawable, @ColorRes int colorResId) {
        int color = ContextCompat.getColor(context, colorResId);
        drawable.setColorFilter(color, PorterDuff.Mode.SRC_IN);
        return drawable;
    }

    /* To reduce boilerplate code */

    /**
     * Drawable for bordered button
     *
     * @param normalColor border color in normal state
     * @return Drawable
     */
    public static Drawable createBorderedDrawable(@ColorRes int normalColor) {
        Drawable normal = createDrawable(
                GradientDrawable.RECTANGLE,
                R.dimen.static_size_3,
                R.dimen.static_size_1,
                normalColor,
                android.R.color.transparent);
        Drawable pressed = createDrawable(
                GradientDrawable.RECTANGLE,
                R.dimen.static_size_3,
                R.dimen.static_size_1,
                R.color.transparent,
                android.R.color.transparent);
        return createDrawableSelector(normal, pressed);
    }
}
