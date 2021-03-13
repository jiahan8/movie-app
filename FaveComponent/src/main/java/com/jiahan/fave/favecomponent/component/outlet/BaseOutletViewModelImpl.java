package com.jiahan.fave.favecomponent.component.outlet;

import android.content.Context;
import android.view.View;

import com.jiahan.fave.core.utils.AppRoute;
import com.jiahan.fave.core.utils.Utils;
import com.jiahan.fave.favecomponent.R;
import com.jiahan.fave.favecomponent.component.outlet.tracker.BaseOutletTracker;
import com.jiahan.fave.favecomponent.entity.BaseCompany;
import com.jiahan.fave.favecomponent.entity.BaseOutlet;

public class BaseOutletViewModelImpl implements BaseOutletViewModel {
    protected final Context           mContext;
    protected final BaseOutletTracker mBaseOutletTracker;
    private final   BaseOutlet        mBaseOutlet;
    private final   BaseCompany       mBaseCompany;

    public BaseOutletViewModelImpl(final Context context,
                                   final BaseOutletTracker baseOutletTracker,
                                   final BaseOutlet baseOutlet,
                                   final BaseCompany baseCompany) {
        mContext = context;
        mBaseOutletTracker = baseOutletTracker;
        mBaseOutlet = baseOutlet;
        mBaseCompany = baseCompany;
    }

    @Override
    public void onItemClicked(final View view) {
        Utils.avoidMultipleClicks(view);
        mBaseOutletTracker.onTapOutlet();
        AppRoute.Launcher.Normal(view.getContext(), AppRoute.OUTLET.getOutletDetailActivityIntent(view.getContext(), mBaseOutlet.mId));
    }

    @Override
    public String getGalleryImage() {
        if (mBaseOutlet.mGalleryImages == null || mBaseOutlet.mGalleryImages.isEmpty()) {
            return mBaseCompany.mLogo;
        }
        return mBaseOutlet.mGalleryImages.get(0);
    }

    @Override
    public String getOutletName() {
        return mBaseOutlet.mName;
    }

    @Override
    public String getFullAddress() {
        return mContext.getString(R.string.address_distance, mBaseOutlet.mAddress, mBaseOutlet.mDistance);
    }

    @Override
    public String getShortAddress() {
        return mContext.getString(R.string.address_distance, mBaseOutlet.mName, mBaseOutlet.mDistance);
    }

    @Override
    public int getAddressEllipsizeIndex() {
        return mBaseOutlet.mDistance.length() + 2;
    }

    @Override
    public String getCashbackRate() {
        return mContext.getString(R.string.percent_cashback_text, mBaseOutlet.mCashbackRate);
    }

    @Override
    public boolean getCashbackRateVisibility() {
        return mBaseOutlet.mCashbackRate > 0;
    }

    @Override
    public boolean getHasFavePay() {
        return mBaseOutlet.mHasFavePay;
    }
}