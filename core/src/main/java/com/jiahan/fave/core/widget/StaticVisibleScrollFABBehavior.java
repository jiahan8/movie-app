package com.jiahan.fave.core.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

public class StaticVisibleScrollFABBehavior extends CoordinatorLayout.Behavior<View> {

    public StaticVisibleScrollFABBehavior(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull final CoordinatorLayout coordinatorLayout,
                                       @NonNull final View child,
                                       @NonNull final View directTargetChild,
                                       @NonNull final View target,
                                       final int axes,
                                       final int type) {
        return true;
    }

    @Override
    public void onNestedScroll(@NonNull final CoordinatorLayout coordinatorLayout,
                               @NonNull final View child,
                               @NonNull final View target,
                               final int dxConsumed,
                               final int dyConsumed,
                               final int dxUnconsumed,
                               final int dyUnconsumed,
                               final int type,
                               @NonNull final int[] consumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type, consumed);
        child.setVisibility(View.VISIBLE);
    }
}
