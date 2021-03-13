package com.jiahan.fave.core.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;

public class RevealAnimation {
    private static final int ANIMATION_DURATION = 300;

    private final View     mView;
    private       Activity mActivity;

    private int revealX;
    private int revealY;

    public RevealAnimation(View view, Intent intent, Activity activity) {
        mView = view;
        mActivity = activity;
        view.setVisibility(View.INVISIBLE);
        revealX = intent.getIntExtra(com.jiahan.fave.core.utils.ActivityTransitionUtils.EXTRA_CIRCULAR_REVEAL_X, 0);
        revealY = intent.getIntExtra(com.jiahan.fave.core.utils.ActivityTransitionUtils.EXTRA_CIRCULAR_REVEAL_Y, 0);
        ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    revealActivity(revealX, revealY);
                    mView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });
        }
    }

    public void revealActivity(int x, int y) {
        float finalRadius = (float) (Math.max(mView.getWidth(), mView.getHeight()) * 1.1);
        Animator circularReveal = ViewAnimationUtils.createCircularReveal(mView, x, y, 0, finalRadius);
        circularReveal.setDuration(ANIMATION_DURATION);
        circularReveal.setInterpolator(new AccelerateInterpolator());
        mView.setVisibility(View.VISIBLE);
        circularReveal.start();
    }

    public void unRevealActivity() {
        float finalRadius = (float) (Math.max(mView.getWidth(), mView.getHeight()) * 1.1);
        Animator circularReveal = ViewAnimationUtils.createCircularReveal(mView, revealX, revealY, finalRadius, 0);
        circularReveal.setDuration(ANIMATION_DURATION);
        circularReveal.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mView.setVisibility(View.INVISIBLE);
                mActivity.finish();
                mActivity.overridePendingTransition(0, 0);
            }
        });
        circularReveal.start();
    }
}