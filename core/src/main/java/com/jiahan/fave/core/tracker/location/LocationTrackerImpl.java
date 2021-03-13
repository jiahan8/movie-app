package com.jiahan.fave.core.tracker.location;

import com.jiahan.fave.core.tracker.EventActions;
import com.jiahan.fave.core.tracker.EventSender;
import com.jiahan.fave.core.tracker.PropertyConstant;

public class LocationTrackerImpl implements LocationTracker {
    private final EventSender mEventSender;
    private final String      mScreenIdentifier;

    public LocationTrackerImpl(final EventSender eventSender,
                               final String screenIdentifier) {
        mEventSender = eventSender;
        mScreenIdentifier = screenIdentifier;
    }

    @Override
    public void onTapSearch(final String location) {
        EventActions actions = mEventSender.tap(PropertyConstant.Value.SEARCH, mScreenIdentifier)
                .addProperty(PropertyConstant.Key.LOCATION, location);
        mEventSender.send(actions);
    }

    @Override
    public void onTapSearchLocation(final String location) {
        EventActions actions = mEventSender.tap(PropertyConstant.Value.CHANGE_LOCATION, mScreenIdentifier)
                .addProperty(PropertyConstant.Key.LOCATION, location);
        mEventSender.send(actions);
    }

    @Override
    public void onTapUserLocation(final String location) {
        EventActions actions = mEventSender.tap(PropertyConstant.Value.DETECT_CURRENT_LOCATION, mScreenIdentifier)
                .addProperty(PropertyConstant.Key.LOCATION, location);
        mEventSender.send(actions);
    }

    @Override
    public void onTapChangeCity() {
        EventActions actions = mEventSender.tap(PropertyConstant.Value.CHANGE_CITY, mScreenIdentifier);
        mEventSender.send(actions);
    }
}