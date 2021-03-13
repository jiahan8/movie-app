package com.jiahan.fave.core.tracker.location;

public interface LocationTracker {
    void onTapSearch(final String location);

    void onTapSearchLocation(final String location);

    void onTapUserLocation(final String location);

    void onTapChangeCity();
}