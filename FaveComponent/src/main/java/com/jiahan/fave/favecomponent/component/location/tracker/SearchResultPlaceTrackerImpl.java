package com.jiahan.fave.favecomponent.component.location.tracker;

import com.jiahan.fave.core.tracker.EventActions;
import com.jiahan.fave.core.tracker.EventSender;
import com.jiahan.fave.core.tracker.PropertyConstant;

public class SearchResultPlaceTrackerImpl implements SearchResultPlaceTracker {
    private final EventSender mEventSender;
    private final String      mScreenIdentifier;

    public SearchResultPlaceTrackerImpl(final EventSender eventSender,
                                        final String screenIdentifier) {
        mEventSender = eventSender;
        mScreenIdentifier = screenIdentifier;
    }

    @Override
    public void onTapPlace(final String place) {
        EventActions actions = mEventSender.tap(PropertyConstant.Value.LOCATION, mScreenIdentifier)
                .addProperty(PropertyConstant.Key.LOCATION, place);
        mEventSender.send(actions);
    }
}