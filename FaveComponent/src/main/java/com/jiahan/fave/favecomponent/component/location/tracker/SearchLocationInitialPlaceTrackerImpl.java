package com.jiahan.fave.favecomponent.component.location.tracker;

import com.jiahan.fave.core.tracker.EventActions;
import com.jiahan.fave.core.tracker.EventSender;
import com.jiahan.fave.core.tracker.PropertyConstant;

public class SearchLocationInitialPlaceTrackerImpl implements SearchLocationInitialPlaceTracker {
    private final EventSender mEventSender;
    private final String      mScreenIdentifier;
    private final String      mSection;

    public SearchLocationInitialPlaceTrackerImpl(final EventSender eventSender,
                                                 final String screenIdentifier,
                                                 final String section) {
        mEventSender = eventSender;
        mScreenIdentifier = screenIdentifier;
        mSection = section;
    }

    @Override
    public void onTapPlace(final String place) {
        EventActions actions = mEventSender.tap(mSection, mScreenIdentifier)
                .addProperty(PropertyConstant.Key.LOCATION, place);
        mEventSender.send(actions);
    }
}