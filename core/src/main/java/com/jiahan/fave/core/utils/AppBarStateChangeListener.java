package com.jiahan.fave.core.utils;

import com.google.android.material.appbar.AppBarLayout;

public abstract class AppBarStateChangeListener implements AppBarLayout.OnOffsetChangedListener {

    public enum State {
        EXPANDED,
        COLLAPSED,
        IDLE
    }

    private State mCurrentState = State.IDLE;

    @Override
    public final void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if (verticalOffset == 0) {
            setCurrentState(appBarLayout, State.EXPANDED);
        }
        else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
            setCurrentState(appBarLayout, State.COLLAPSED);
        }
        else {
            setCurrentState(appBarLayout, State.IDLE);
        }
    }

    private void setCurrentState(AppBarLayout appBarLayout, State state) {
        if (mCurrentState != state) {
            onStateChanged(appBarLayout, state);
        }
        mCurrentState = state;
    }

    public abstract void onStateChanged(AppBarLayout appBarLayout, State state);
}
