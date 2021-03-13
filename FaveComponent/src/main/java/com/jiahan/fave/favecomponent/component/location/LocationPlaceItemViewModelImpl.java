package com.jiahan.fave.favecomponent.component.location;

import android.content.Context;
import android.view.View;

import com.jiahan.fave.core.common.BaseActivity;
import com.jiahan.fave.core.entity.Place;
import com.jiahan.fave.core.utils.Constant;
import com.jiahan.fave.core.utils.liveDataEventBus.LiveDataEventBus;
import com.jiahan.fave.favecomponent.component.location.tracker.SearchLocationInitialPlaceTracker;

public class LocationPlaceItemViewModelImpl implements LocationPlaceItemViewModel {
    private final Context                           mContext;
    private final SearchLocationInitialPlaceTracker mTracker;
    private final Place                             mPlace;

    public LocationPlaceItemViewModelImpl(final Context context,
                                          final SearchLocationInitialPlaceTracker tracker,
                                          final Place place) {
        mContext = context;
        mTracker = tracker;
        mPlace = place;
    }

    @Override
    public String getPlace() {
        return mPlace.mName;
    }

    @Override
    public void onItemClicked(final View view) {
        mTracker.onTapPlace(getPlace());
        LiveDataEventBus.getInstance()
                .with(Constant.CHANGE_PLACE, Place.class)
                .postValue(mPlace);
        ((BaseActivity) mContext).finish();
    }
}