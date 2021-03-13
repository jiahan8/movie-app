package com.jiahan.fave.favecomponent.component.outlet.tracker;

import com.jiahan.fave.core.tracker.EventActions;
import com.jiahan.fave.core.tracker.EventSender;
import com.jiahan.fave.core.tracker.PropertyConstant;
import com.jiahan.fave.favecomponent.entity.Outlet;

public class OutletTrackerImpl extends BaseOutletTrackerImpl implements OutletTracker {
    public OutletTrackerImpl(final Outlet outlet,
                             final EventSender eventSender,
                             final String screenIdentifier,
                             final String sectionName,
                             final int position) {
        super(outlet, eventSender, screenIdentifier, sectionName, position);
    }

    @Override
    public void onTapFavourite() {
        EventActions actions = mEventSender.tap(PropertyConstant.Value.FAVE_COMPANY, mScreenIdentifier)
                .addProperty(PropertyConstant.Key.COMPANY_NAME, mOutlet.mCompany.mName);
        mEventSender.send(actions);
    }

    @Override
    public void onTapOpeningHours() {
        EventActions actions = mEventSender.tap(PropertyConstant.Value.SHOW_OPENING_HOURS, mScreenIdentifier)
                .addProperty(PropertyConstant.Key.POSITION, mPosition);
        mEventSender.send(actions);
    }

    @Override
    public void onTapPayNow() {
        EventActions actions = mEventSender.tap(PropertyConstant.Value.PAY_NOW, mScreenIdentifier)
                .addProperty(PropertyConstant.Key.COMPANY_NAME, mOutlet.mCompany.mName);
        mEventSender.send(actions);
    }
}