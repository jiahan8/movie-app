package com.jiahan.fave.favecomponent.component.needHelp;

import android.graphics.drawable.Drawable;
import android.view.View;

import com.jiahan.fave.core.utils.AppRoute;
import com.jiahan.fave.core.utils.drawableUtils.WidgetDrawable;
import com.jiahan.fave.favecomponent.component.needHelp.tracker.NeedHelpTracker;

public class NeedHelpItemViewModelImpl implements NeedHelpItemViewModel {
    private final NeedHelpTracker mTracker;

    public NeedHelpItemViewModelImpl(final NeedHelpTracker tracker) {
        mTracker = tracker;
    }

    @Override
    public void onTalkToUsClicked(View v) {
        mTracker.onTapLiveChat();
        AppRoute.Launcher.Normal(v.getContext(), AppRoute.OLD.getTalkToUSFAQActivityIntent(v.getContext()));
    }

    @Override
    public Drawable getTalkToUsBackground() {
        return WidgetDrawable.getPinkBorderBackground();
    }
}