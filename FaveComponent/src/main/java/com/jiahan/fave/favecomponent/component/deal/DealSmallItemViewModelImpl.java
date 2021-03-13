package com.jiahan.fave.favecomponent.component.deal;

import android.content.Context;

import com.jiahan.fave.favecomponent.component.deal.tracker.DealTracker;
import com.jiahan.fave.favecomponent.component.outlet.OutletSmallItemViewModel;
import com.jiahan.fave.favecomponent.component.outlet.OutletSmallItemViewModelImpl;
import com.jiahan.fave.favecomponent.entity.Deal;

public class DealSmallItemViewModelImpl extends DealViewModelImpl implements DealSmallItemViewModel {
    private final Deal        mDeal;
    private final DealTracker mDealTracker;
    private final boolean     mShowDivider;
    private final boolean     mShowAddress;

    public DealSmallItemViewModelImpl(final Context context,
                                      final Deal deal,
                                      final DealTracker dealTracker,
                                      final boolean showDivider,
                                      final boolean showAddress) {
        super(context, deal, dealTracker);
        mDeal = deal;
        mDealTracker = dealTracker;
        mShowDivider = showDivider;
        mShowAddress = showAddress;
    }

    @Override
    public OutletSmallItemViewModel getOutletViewModel() {
        return new OutletSmallItemViewModelImpl(mContext, mDealTracker.getOutletTracker(), mDeal.mOutlet, true, mShowAddress);
    }

    @Override
    public boolean getDividerVisibility() {
        return mShowDivider;
    }
}