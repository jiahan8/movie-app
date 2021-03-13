package com.jiahan.fave.favecomponent.component.needHelp.tracker;

import com.jiahan.fave.core.tracker.EventActions;
import com.jiahan.fave.core.tracker.EventSender;
import com.jiahan.fave.core.tracker.PropertyConstant;

public class NeedHelpTrackerImpl implements NeedHelpTracker {
    private final EventSender mEventSender;
    private final String      mScreenIdentifier;

    public NeedHelpTrackerImpl(final EventSender eventSender,
                               final String screenIdentifier) {
        mEventSender = eventSender;
        mScreenIdentifier = screenIdentifier;
    }

    @Override
    public void onTapLiveChat() {
        EventActions actions = mEventSender.tap(PropertyConstant.Value.LIVE_CHAT, mScreenIdentifier);
        mEventSender.send(actions);
    }
}