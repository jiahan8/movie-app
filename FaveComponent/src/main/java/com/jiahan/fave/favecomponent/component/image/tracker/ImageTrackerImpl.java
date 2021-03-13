package com.jiahan.fave.favecomponent.component.image.tracker;

import com.jiahan.fave.core.tracker.EventActions;
import com.jiahan.fave.core.tracker.EventSender;
import com.jiahan.fave.core.tracker.PropertyConstant;

public class ImageTrackerImpl implements ImageTracker {
    private final EventSender mEventSender;
    private final String      mScreenIdentifier;
    private final int         mPosition;

    public ImageTrackerImpl(final EventSender eventSender,
                            final String screenIdentifier,
                            final int position) {
        mEventSender = eventSender;
        mScreenIdentifier = screenIdentifier;
        mPosition = position;
    }

    @Override
    public void onTapImage() {
        EventActions actions = mEventSender.tap(PropertyConstant.Value.VIEW_ALL_PHOTOS, mScreenIdentifier)
                .addProperty(PropertyConstant.Key.POSITION, mPosition);
        mEventSender.send(actions);
    }
}