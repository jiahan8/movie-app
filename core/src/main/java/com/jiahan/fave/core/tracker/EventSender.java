package com.jiahan.fave.core.tracker;

import android.content.Context;

import com.jiahan.fave.core.utils.Logger;

public class EventSender {
    private final CleverTap mCleverTap;

    public EventSender(final Context context) {
        mCleverTap = new CleverTap(context);
    }

    public EventActions screenDisplayed(String screen) {
        EventActions eventActions = EventActions.create(PropertyConstant.Name.SCREEN_DISPLAYED);
        eventActions.addProperty(PropertyConstant.Key.EVENT_PROPERTY_SCREEN_NAME, screen);
        return eventActions;
    }

    public EventActions tap(String tappedOn, String screen) {
        EventActions eventActions = EventActions.create(PropertyConstant.Name.TAP);
        eventActions.addProperty(PropertyConstant.Key.EVENT_PROPERTY_TAPPED_ON, tappedOn);
        eventActions.addProperty(PropertyConstant.Key.EVENT_PROPERTY_SCREEN_NAME, screen);
        return eventActions;
    }

    public EventActions customEvent(String eventName, String screen) {
        EventActions eventActions = EventActions.create(eventName);
        eventActions.addProperty(PropertyConstant.Key.EVENT_PROPERTY_SCREEN_NAME, screen);
        return eventActions;
    }

    public void send(final EventActions eventActions) {
        Logger.log("Sending CleverTap Event: " + eventActions.toString());
        mCleverTap.pushEvent(eventActions);
    }
}