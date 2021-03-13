package com.jiahan.fave.favecomponent.component.location.tracker;

import com.jiahan.fave.core.tracker.EventActions;
import com.jiahan.fave.core.tracker.EventSender;
import com.jiahan.fave.core.tracker.PropertyConstant;

public class ChangeCityTrackerImpl implements ChangeCityTracker {
    private final EventSender mEventSender;
    private final String      mScreenIdentifier;
    private final String      mFromScreen;

    public ChangeCityTrackerImpl(final EventSender eventSender,
                                 final String screenIdentifier,
                                 final String fromScreen) {
        mEventSender = eventSender;
        mScreenIdentifier = screenIdentifier;
        mFromScreen = fromScreen;
    }

    @Override
    public void onTapCity(final String city, final boolean sameCity) {
        EventActions actions = mEventSender.tap(PropertyConstant.Value.CHANGE_CITY, mScreenIdentifier)
                .addProperty(PropertyConstant.Key.CITY, city)
                .addProperty(PropertyConstant.Key.FROM_SCREEN_PAGE, mFromScreen)
                .addProperty(PropertyConstant.Key.SAME_CITY_CLICKED, sameCity);
        mEventSender.send(actions);
    }
}