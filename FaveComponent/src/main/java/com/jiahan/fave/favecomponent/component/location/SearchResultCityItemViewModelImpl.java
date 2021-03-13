package com.jiahan.fave.favecomponent.component.location;

import android.content.Context;
import android.view.View;

import com.jiahan.fave.core.common.BaseActivity;
import com.jiahan.fave.core.entity.City;
import com.jiahan.fave.core.utils.Constant;
import com.jiahan.fave.core.utils.liveDataEventBus.LiveDataEventBus;
import com.jiahan.fave.favecomponent.R;
import com.jiahan.fave.favecomponent.component.location.tracker.SearchResultCityTracker;

public class SearchResultCityItemViewModelImpl implements SearchResultItemViewModel {
    private final Context                 mContext;
    private final SearchResultCityTracker mTracker;
    private final City                    mCity;

    public SearchResultCityItemViewModelImpl(final Context context,
                                             final SearchResultCityTracker tracker,
                                             final City city) {
        mContext = context;
        mTracker = tracker;
        mCity = city;
    }

    @Override
    public int getIconResource() {
        return R.drawable.ic_city;
    }

    @Override
    public String getName() {
        return mCity.name;
    }

    @Override
    public String getAddress() {
        return mCity.mAddress;
    }

    @Override
    public void onItemClicked(final View view) {
        mTracker.onTapCity(getName());
        LiveDataEventBus.getInstance()
                .with(Constant.CHANGE_CITY, City.class)
                .postValue(mCity);
        ((BaseActivity) mContext).finish();
    }
}