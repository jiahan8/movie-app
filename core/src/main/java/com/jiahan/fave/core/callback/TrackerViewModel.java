package com.jiahan.fave.core.callback;

import com.jiahan.fave.core.tracker.EventSender;

public interface TrackerViewModel {
    EventSender getEventSender();

    String getScreenIdentifier();
}