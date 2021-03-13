package com.jiahan.fave.favecomponent.component.deal;

import android.content.Context;

import com.jiahan.fave.favecomponent.component.deal.tracker.DealTracker;
import com.jiahan.fave.favecomponent.component.outlet.OutletViewModel;
import com.jiahan.fave.favecomponent.component.outlet.OutletViewModelImpl;
import com.jiahan.fave.favecomponent.entity.Deal;
import com.jiahan.fave.favecomponent.interactor.DealInteractor;

public class DealXLargeViewModelImpl extends DealViewModelImpl implements DealXLargeViewModel {
    private final DealInteractor mInteractor;
    private final Deal           mDeal;
    private final DealTracker    mDealTracker;

    public DealXLargeViewModelImpl(final Context context,
                                   final DealInteractor interactor,
                                   final Deal deal,
                                   final DealTracker dealTracker) {
        super(context, deal, dealTracker);
        mInteractor = interactor;
        mDeal = deal;
        mDealTracker = dealTracker;
    }

    @Override
    public OutletViewModel getOutletViewModel() {
        return new OutletViewModelImpl(mContext, mInteractor, mDealTracker.getOutletTracker(), mDeal.mOutlet);
    }

    @Override
    public String getWhatYouGetHtml() {
        return mDeal.mWhatYouGetHtmlSection;
    }

    @Override
    public String getPurchaseTitle() {
        return mDeal.mPurchaseDetails.mPurchaseTitle;
    }

    @Override
    public boolean getPurchaseEnabled() {
        return mDeal.mPurchaseDetails != null && mDeal.mPurchaseDetails.isAvailable;
    }
}