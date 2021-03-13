package com.jiahan.fave.favecomponent.component.assortment.tracker;

import android.text.TextUtils;

import com.jiahan.fave.core.tracker.EventActions;
import com.jiahan.fave.core.tracker.EventSender;
import com.jiahan.fave.core.tracker.PropertyConstant;
import com.jiahan.fave.core.utils.StringUtil;
import com.jiahan.fave.favecomponent.entity.Assortment;

public abstract class BaseAssortmentTracker {
    protected final Assortment  mAssortment;
    protected final EventSender mEventSender;
    protected final String      mScreenIdentifier;
    private final   int         mPosition;

    public BaseAssortmentTracker(final Assortment assortment,
                                 final EventSender eventSender,
                                 final String screenIdentifier,
                                 final int position) {
        mAssortment = assortment;
        mEventSender = eventSender;
        mScreenIdentifier = screenIdentifier;
        mPosition = position;
    }

    protected EventActions getAssortmentEventActions(String tappedOn) {
        EventActions actions = mEventSender.tap(tappedOn, mScreenIdentifier)
                .addProperty(PropertyConstant.Key.ASSORTMENT_NAME, mAssortment.mName)
                .addProperty(PropertyConstant.Key.POSITION, mPosition);
        actions.addProperty(PropertyConstant.Key.ASSORTMENT_RIBBON, TextUtils.isEmpty(mAssortment.mRibbonLabel) ?
                PropertyConstant.Value.NONE :
                mAssortment.mRibbonLabel);
        if (!mAssortment.mAssortmentTypeList.isEmpty()) {
            actions.addProperty(PropertyConstant.Key.ASSORTMENT_TYPE, StringUtil.removeAllNonAlphanumeric(mAssortment.mAssortmentTypeList.get(0)));
        }
        return actions;
    }
}