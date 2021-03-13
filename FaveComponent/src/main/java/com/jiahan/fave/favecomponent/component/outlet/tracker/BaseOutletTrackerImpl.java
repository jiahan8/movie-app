package com.jiahan.fave.favecomponent.component.outlet.tracker;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.jiahan.fave.core.tracker.EventActions;
import com.jiahan.fave.core.tracker.EventSender;
import com.jiahan.fave.core.tracker.PropertyConstant;
import com.jiahan.fave.favecomponent.entity.Assortment;
import com.jiahan.fave.favecomponent.entity.Outlet;

import org.jetbrains.annotations.NotNull;

public class BaseOutletTrackerImpl implements BaseOutletTracker {
    protected final Outlet      mOutlet;
    protected final EventSender mEventSender;
    protected final String      mScreenIdentifier;
    protected final String      mSectionName;
    protected final int         mPosition;

    private Assortment mAssortment;
    private String     mCategory;
    private String     mProduct;
    private String     mAppFilter;
    private String     mLocation;
    private String     mAppliedSorting;
    private Boolean    mAppliedFilter;

    public BaseOutletTrackerImpl(final Outlet outlet,
                                 final EventSender eventSender,
                                 final String screenIdentifier,
                                 final String sectionName,
                                 final int position) {
        mOutlet = outlet;
        mEventSender = eventSender;
        mScreenIdentifier = screenIdentifier;
        mSectionName = sectionName;
        mPosition = position;
    }

    @Override
    public void onTapOutlet() {
        EventActions actions = mEventSender.tap(PropertyConstant.Value.SHOW_OUTLET_DETAIL, mScreenIdentifier)
                .addProperty(PropertyConstant.Key.SECTION_NAME, mSectionName)
                .addProperty(PropertyConstant.Key.POSITION, mPosition)
                .addProperty(PropertyConstant.Key.OUTLET_ID, mOutlet.mId)
                .addProperty(PropertyConstant.Key.COMPANY_ID, mOutlet.mCompany.mId)
                .addProperty(PropertyConstant.Key.COMPANY_NAME, mOutlet.mCompany.mName);
        if (mAssortment != null) {
            actions.addProperty(PropertyConstant.Key.ASSORTMENT_NAME, mAssortment.mName);
            actions.addProperty(PropertyConstant.Key.ASSORTMENT_RIBBON, mAssortment.mRibbonLabel);
            if (!mAssortment.mAssortmentTypeList.isEmpty()) {
                actions.addProperty(PropertyConstant.Key.ASSORTMENT_TYPE, mAssortment.mAssortmentTypeList.get(0));
            }
        }
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
    public void setAssortment(@NotNull final Assortment assortment) {
        mAssortment = assortment;
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