package com.jiahan.fave.favecomponent.component.location;

import android.content.Context;
import android.view.View;

import com.jiahan.fave.core.common.BaseActivity;
import com.jiahan.fave.core.entity.Place;
import com.jiahan.fave.core.utils.Constant;
import com.jiahan.fave.core.utils.liveDataEventBus.LiveDataEventBus;
import com.jiahan.fave.favecomponent.R;
import com.jiahan.fave.favecomponent.component.location.tracker.SearchResultPlaceTracker;

public class SearchResultPlaceItemViewModelImpl implements SearchResultItemViewModel {
    private final Context                  mContext;
    private final SearchResultPlaceTracker mTracker;
    private final Place                    mPlace;

    public SearchResultPlaceItemViewModelImpl(final Context context,
                                              final SearchResultPlaceTracker tracker,
                                              final Place place) {
        mContext = context;
        mTracker = tracker;
        mPlace = place;
    }

    @Override
    public int getIconResource() {
        return R.drawable.ic_location_pink;
    }

    @Override
    public String getName() {
        return mPlace.mName;
    }

    @Override
    public String getAddress() {
        return mPlace.mAddress;
    }

    @Override
    public void onItemClicked(final View view) {
        mTracker.onTapPlace(getName());
        LiveDataEventBus.getInstance()
                .with(Constant.CHANGE_PLACE, Place.class)
                .postValue(mPlace);
        ((BaseActivity) mContext).finish();
    }
}