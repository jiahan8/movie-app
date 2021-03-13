package com.jiahan.fave.favecomponent.component.banner.tracker;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.jiahan.fave.core.tracker.EventActions;
import com.jiahan.fave.core.tracker.EventSender;
import com.jiahan.fave.core.tracker.PropertyConstant;
import com.jiahan.fave.favecomponent.entity.Banner;

public class BannerTrackerImpl implements BannerTracker {
    private final Banner      mBanner;
    private final EventSender mEventSender;
    private final String      mScreenIdentifier;
    private final String      mSectionName;
    private final int         mPosition;

    private String  mCategory;
    private String  mProduct;
    private String  mAppFilter;
    private String  mLocation;
    private String  mAppliedSorting;
    private Boolean mAppliedFilter;

    public BannerTrackerImpl(final Banner banner,
                             final EventSender eventSender,
                             final String screenIdentifier,
                             final String sectionName,
                             final int position) {
        mBanner = banner;
        mEventSender = eventSender;
        mScreenIdentifier = screenIdentifier;
        mSectionName = sectionName;
        mPosition = position;
    }

    @Override
    public void onTapBanner() {
        EventActions actions = mEventSender.tap(PropertyConstant.Value.SHOW_BANNER, mScreenIdentifier)
                .addProperty(PropertyConstant.Key.SECTION_NAME, mSectionName)
                .addProperty(PropertyConstant.Key.POSITION, mPosition)
                .addProperty(PropertyConstant.Key.BANNER, mBanner.mName);
        if (!TextUtils.isEmpty(mCategory)) {
            actions.addProperty(PropertyConstant.Key.CATEGORY, mCategory);
        }
        if (!TextUtils.isEmpty(mProduct)) {
            actions.addProperty(PropertyConstant.Key.PRODUCT, mProduct);
        }
        if (!TextUtils.isEmpty(mAppFilter)) {
            actions.addProperty(PropertyConstant.Key.APP_FILTER, mAppFilter);
        }
        if (!TextUtils.isEmpty(mLocation)) {
            actions.addProperty(PropertyConstant.Key.LOCATION, mLocation);
        }
        if (!TextUtils.isEmpty(mAppliedSorting)) {
            actions.addProperty(PropertyConstant.Key.SORT_APPLIED, mAppliedSorting);
        }
        if (mAppliedFilter != null) {
            actions.addProperty(PropertyConstant.Key.FILTER_APPLIED, mAppliedFilter);
        }
        mEventSender.send(actions);
    }

    @Override
    public void setCategory(@NonNull final String category,
                            @NonNull final String product,
                            @NonNull final String appFilter,
                            @NonNull final String location,
                            final String appliedSorting,
                            final boolean appliedFilter) {
        mCategory = category;
        mProduct = product;
        mAppFilter = appFilter;
        mLocation = location;
        mAppliedSorting = appliedSorting;
        mAppliedFilter = appliedFilter;
    }
}