package com.jiahan.fave.favecomponent.component.assortment.tracker;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.jiahan.fave.core.tracker.EventActions;
import com.jiahan.fave.core.tracker.EventSender;
import com.jiahan.fave.core.tracker.PropertyConstant;
import com.jiahan.fave.core.utils.StringUtil;
import com.jiahan.fave.favecomponent.entity.Assortment;

public class AssortmentTrackerImpl extends BaseAssortmentTracker implements AssortmentTracker {
    private final String mTappedOn;

    private String  mCategory;
    private String  mProduct;
    private String  mAppFilter;
    private String  mLocation;
    private String  mAppliedSorting;
    private Boolean mAppliedFilter;

    public AssortmentTrackerImpl(final Assortment assortment,
                                 final EventSender eventSender,
                                 final String screenIdentifier,
                                 final int position,
                                 final String tappedOn) {
        super(assortment, eventSender, screenIdentifier, position);
        mTappedOn = tappedOn;
    }

    @Override
    public void onTapAssortment() {
        EventActions actions = getAssortmentEventActions(mTappedOn);
        if (!TextUtils.isEmpty(mCategory)) {
            actions.addProperty(PropertyConstant.Key.CATEGORY, mCategory);
        }
        if (!TextUtils.isEmpty(mProduct)) {
            actions.addProperty(PropertyConstant.Key.PRODUCT, StringUtil.removeAllNonAlphanumeric(mProduct));
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
    public void setLocation(@NonNull final String location) {
        mLocation = location;
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
        mAppliedSorting = appliedSorting;
        mAppliedFilter = appliedFilter;
        setLocation(location);
    }
}