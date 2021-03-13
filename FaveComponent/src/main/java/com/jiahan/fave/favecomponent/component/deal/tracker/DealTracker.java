package com.jiahan.fave.favecomponent.component.deal.tracker;

import com.jiahan.fave.favecomponent.component.outlet.tracker.OutletTracker;

public interface DealTracker extends BaseDealTracker {
    void onTapFavourite();

    OutletTracker getOutletTracker();
}