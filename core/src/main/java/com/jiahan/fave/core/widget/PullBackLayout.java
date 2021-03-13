package com.jiahan.fave.core.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.customview.widget.ViewDragHelper;

import org.jetbrains.annotations.NotNull;

public class PullBackLayout extends FrameLayout {
    private final int mMinimumFlingVelocity;

    @Nullable
    private OnPullListener mOnPullListener;
    private ViewDragHelper mDragger;

    public PullBackLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public PullBackLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PullBackLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mMinimumFlingVelocity = ViewConfiguration.get(context).getScaledMinimumFlingVelocity();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (mDragger == null) {
            return super.onInterceptTouchEvent(ev);
        }
        return mDragger.shouldInterceptTouchEvent(ev);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        if (mDragger == null) {
            return super.onTouchEvent(event);
        }
        mDragger.processTouchEvent(event);
        return true;
    }

    @Override
    public void computeScroll() {
        if (mDragger == null) {
            return;
        }
        if (mDragger.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void setOnPullListener(@Nullable OnPullListener onPullListener) {
        if (onPullListener == null) {
            return;
        }
        mDragger = ViewDragHelper.create(this, 0.125F, new ViewDragCallback());
        mOnPullListener = onPullListener;
    }

    private class ViewDragCallback extends ViewDragHelper.Callback {
        private ViewDragCallback() {
        }

        @Override
        public boolean tryCaptureView(@NotNull View child, int pointerId) {
            return true;
        }

        @Override
        public int clampViewPositionHorizontal(@NotNull View child, int left, int dx) {
            return 0;
        }

        @Override
        public int clampViewPositionVertical(@NotNull View child, int top, int dy) {
            return Math.max(0, top);
        }

        @Override
        public int getViewHorizontalDragRange(@NotNull View child) {
            return 0;
        }

        @Override
        public int getViewVerticalDragRange(@NotNull View child) {
            return getHeight();
        }

        @Override
        public void onViewCaptured(@NotNull View capturedChild, int activePointerId) {
            if (mOnPullListener != null) {
                mOnPullListener.onPullStart();
            }
        }

        @Override
        public void onViewPositionChanged(@NotNull View changedView, int left, int top, int dx, int dy) {
            if (mOnPullListener != null) {
                mOnPullListener.onPull((float) top / (float) getHeight());
            }
        }

        @Override
        public void onViewReleased(@NotNull View releasedChild, float xVelocity, float yVelocity) {
            if (mDragger == null) {
                return;
            }
            int slop = yVelocity > (float) mMinimumFlingVelocity ? getHeight() / 6 : getHeight() / 3;
            if (releasedChild.getTop() > slop) {
                if (mOnPullListener != null) {
                    mOnPullListener.onPullComplete();
                }
            }
            else {
                if (mOnPullListener != null) {
                    mOnPullListener.onPullCancel();
                }
                mDragger.settleCapturedViewAt(0, 0);
                invalidate();
            }
        }
    }

    public interface OnPullListener {
        void onPullStart();

        void onPull(float newYPosition);

        void onPullCancel();

        void onPullComplete();
    }
}