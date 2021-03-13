package com.jiahan.fave.favecomponent.component.location;

import android.content.Context;
import android.view.View;

import com.jiahan.fave.core.common.BaseActivity;
import com.jiahan.fave.core.entity.City;
import com.jiahan.fave.core.utils.Constant;
import com.jiahan.fave.core.utils.Utils;
import com.jiahan.fave.core.utils.liveDataEventBus.LiveDataEventBus;
import com.jiahan.fave.favecomponent.component.location.tracker.ChangeCityTracker;

public class CityItemViewModelImpl implements CityItemViewModel {
    private final Context           mContext;
    private final ChangeCityTracker mTracker;
    private final City              mCity;
    private final boolean           mSameCity;

    public CityItemViewModelImpl(final Context context,
                                 final ChangeCityTracker tracker,
                                 final City city,
                                 final boolean sameCity) {
        mContext = context;
        mTracker = tracker;
        mCity = city;
        mSameCity = sameCity;
    }

    @Override
    public String getName() {
        return mCity.name;
    }

    @Override
    public boolean getSelected() {
        return mSameCity;
    }

    @Override
    public void onItemClicked(final View view) {
        mTracker.onTapCity(mCity.name, mSameCity);
        if (mSameCity) {
            Utils.vibrate();
            return;
        }
        LiveDataEventBus.getInstance()
                .with(Constant.CHANGE_CITY, City.class)
                .postValue(mCity);
        ((BaseActivity) mContext).finish();
    }
}