package com.jiahan.fave.favecomponent.component.deal;

import android.content.Context;

import com.jiahan.fave.favecomponent.component.deal.tracker.DealTracker;
import com.jiahan.fave.favecomponent.entity.Deal;

public class DealViewModelImpl extends BaseDealViewModelImpl implements DealViewModel {
    private final Deal mDeal;

    public DealViewModelImpl(final Context context,
                             final Deal deal,
                             final DealTracker dealTracker) {
        super(context, deal, dealTracker);
        mDeal = deal;
    }

    @Override
    public boolean getTrendingVisibility() {
        return !getFlashVisibility() && mDeal.mTrending;
    }

    @Override
    public boolean getFlashVisibility() {
        return mDeal.mFlash;
    }
}