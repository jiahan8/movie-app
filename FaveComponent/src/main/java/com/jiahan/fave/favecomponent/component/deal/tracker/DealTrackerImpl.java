package com.jiahan.fave.favecomponent.component.deal.tracker;

import com.jiahan.fave.core.tracker.EventActions;
import com.jiahan.fave.core.tracker.EventSender;
import com.jiahan.fave.core.tracker.PropertyConstant;
import com.jiahan.fave.favecomponent.component.outlet.tracker.OutletTracker;
import com.jiahan.fave.favecomponent.component.outlet.tracker.OutletTrackerImpl;
import com.jiahan.fave.favecomponent.entity.Deal;

public class DealTrackerImpl extends BaseDealTrackerImpl implements DealTracker {
    public DealTrackerImpl(final Deal deal,
                           final EventSender eventSender,
                           final String screenIdentifier,
                           final String sectionName,
                           final int position) {
        super(deal, eventSender, screenIdentifier, sectionName,position);
    }

    @Override
    public void onTapFavourite() {
        EventActions actions = mEventSender.tap(PropertyConstant.Value.ADD_WISHLIST, mScreenIdentifier)
                .addProperty(PropertyConstant.Key.OFFER_NAME, mDeal.mName);
        mEventSender.send(actions);
    }

    @Override
    public OutletTracker getOutletTracker() {
        return new OutletTrackerImpl(mDeal.mOutlet, mEventSender, mScreenIdentifier, mSectionName, mPosition);
    }
}