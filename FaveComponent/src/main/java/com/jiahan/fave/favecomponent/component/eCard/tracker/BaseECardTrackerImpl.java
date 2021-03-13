package com.jiahan.fave.favecomponent.component.eCard.tracker;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.jiahan.fave.core.tracker.EventActions;
import com.jiahan.fave.core.tracker.EventSender;
import com.jiahan.fave.core.tracker.PropertyConstant;
import com.jiahan.fave.favecomponent.entity.BaseECard;
import com.jiahan.fave.favecomponent.entity.Product;

public class BaseECardTrackerImpl implements BaseECardTracker {
    protected final BaseECard   mECard;
    protected final EventSender mEventSender;
    protected final String      mScreenIdentifier;
    protected final String      mSectionName;
    protected final int         mPosition;

    private String  mCategory;
    private String  mAppFilter;
    private String  mLocation;
    private String  mAppliedSorting;
    private Boolean mAppliedFilter;

    public BaseECardTrackerImpl(final BaseECard eCard,
                                final EventSender eventSender,
                                final String screenIdentifier,
                                final String sectionName,
                                final int position) {
        mECard = eCard;
        mEventSender = eventSender;
        mScreenIdentifier = screenIdentifier;
        mSectionName = sectionName;
        mPosition = position;
    }

    @Override
    public void onTapECard() {
        EventActions actions = mEventSender.tap(PropertyConstant.Value.VIEW_ECARD_DETAIL, mScreenIdentifier)
                .addProperty(PropertyConstant.Key.SECTION_NAME, mSectionName)
                .addProperty(PropertyConstant.Key.POSITION, mPosition)
                .addProperty(PropertyConstant.Key.ECARD_ID, mECard.mId)
                .addProperty(PropertyConstant.Key.COMPANY_NAME, mECard.mCompanyName);
        if (!TextUtils.isEmpty(mCategory)) {
            actions.addProperty(PropertyConstant.Key.CATEGORY, mCategory);
        }
        actions.addProperty(PropertyConstant.Key.PRODUCT, Product.ProductType.ECARD.getValue());
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
                            @NonNull final String appFilter,
                            @NonNull final String location,
                            final String appliedSorting,
                            final boolean appliedFilter) {
        mCategory = category;
        mAppFilter = appFilter;
        mLocation = location;
        mAppliedSorting = appliedSorting;
        mAppliedFilter = appliedFilter;
    }
}