package com.jiahan.fave.favecomponent.component.banner;

import android.text.TextUtils;
import android.view.View;

import com.jiahan.fave.core.utils.AppRoute;
import com.jiahan.fave.favecomponent.component.banner.tracker.BannerTracker;
import com.jiahan.fave.favecomponent.entity.Banner;

public class BannerViewModelImpl implements BannerViewModel {
    private final BannerTracker mTracker;
    private final Banner        mBanner;

    public BannerViewModelImpl(final BannerTracker tracker, final Banner banner) {
        mTracker = tracker;
        mBanner = banner;
    }

    @Override
    public String getBannerImage() {
        return mBanner.mAppImageUrl;
    }

    @Override
    public void onItemClicked(final View view) {
        if (!TextUtils.isEmpty(mBanner.mDeeplink)) {
            mTracker.onTapBanner();
            AppRoute.Launcher.Normal(view.getContext(), AppRoute.OLD.getDeepLinkingActivityIntent(view.getContext(), mBanner.mDeeplink));
        }
    }
}