package com.jiahan.fave.core.widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.motion.widget.MotionLayout;

import com.google.android.material.appbar.AppBarLayout;

public class MotionToolbar extends MotionLayout implements AppBarLayout.OnOffsetChangedListener {
    public MotionToolbar(@NonNull final Context context) {
        super(context);
        init();
    }

    public MotionToolbar(@NonNull final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MotionToolbar(@NonNull final Context context, @Nullable final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    public void onOffsetChanged(final AppBarLayout appBarLayout, final int verticalOffset) {
        setProgress(-verticalOffset / (appBarLayout.getTotalScrollRange() * 1F));
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ((AppBarLayout) getParent()).addOnOffsetChangedListener(this);
    }

    private void init() {
//        setDebugMode(BuildConfig.DEBUG ? MotionLayout.DEBUG_SHOW_PATH : MotionLayout.DEBUG_SHOW_NONE);
    }
}