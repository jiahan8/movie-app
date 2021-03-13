package com.jiahan.fave.favecomponent.component.location.tracker;

import com.jiahan.fave.core.tracker.EventActions;
import com.jiahan.fave.core.tracker.EventSender;
import com.jiahan.fave.core.tracker.PropertyConstant;

public class SearchResultCityTrackerImpl implements SearchResultCityTracker {
    private final EventSender mEventSender;
    private final String      mScreenIdentifier;

    public SearchResultCityTrackerImpl(final EventSender eventSender,
                                       final String screenIdentifier) {
        mEventSender = eventSender;
        mScreenIdentifier = screenIdentifier;
    }

    @Override
    public void onTapCity(final String city) {
        EventActions actions = mEventSender.tap(PropertyConstant.Value.FRINGE_CITY, mScreenIdentifier)
                .addProperty(PropertyConstant.Key.CITY, city);
        mEventSender.send(actions);
    }
}