package com.jiahan.fave.core.tracker;

import com.jiahan.fave.core.utils.Utils;
import com.jiahan.fave.core.utils.gson.GsonUtils;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventActions {
    private final String              mEventName;
    private final Map<String, Object> mProperties;

    public static EventActions create(String eventName) {
        return new EventActions(eventName);
    }

    protected EventActions(String eventName) {
        mEventName = eventName;
        mProperties = new HashMap<>();
    }

    public String getEventName() {
        return mEventName;
    }

    public Map<String, Object> getProperties() {
        return mProperties;
    }

    public EventActions addProperty(final String key, final String value) {
        mProperties.put(key, value);
        return this;
    }

    public EventActions addProperty(final String key, final Number value) {
        mProperties.put(key, value);
        return this;
    }

    public EventActions addProperty(final String key, final Boolean value) {
        mProperties.put(key, value);
        return this;
    }

    public EventActions addProperty(final String key, final String[] value) {
        mProperties.put(key, Utils.join(", ", value));
        return this;
    }

    public EventActions addProperty(final String key, final List<String> value) {
        mProperties.put(key, Utils.join(", ", value));
        return this;
    }

    public EventActions addProperty(final String key, final Number[] value) {
        mProperties.put(key, value);
        return this;
    }

    @Override
    public boolean equals(final Object o) {
        if (o instanceof EventActions) {
            EventActions analyticsEvent = (EventActions) o;
            return analyticsEvent.getEventName().equals(mEventName) && analyticsEvent.getProperties().equals(mProperties);
        }
        else {
            return false;
        }
    }

    @NotNull
    @Override
    public String toString() {
        return GsonUtils.getGson().toJson(this, EventActions.class);
    }
}