package com.jiahan.fave.favecomponent.component.outlet;

import android.content.Context;

import com.jiahan.fave.core.utils.DateTimeUtils;
import com.jiahan.fave.favecomponent.R;
import com.jiahan.fave.favecomponent.component.company.BaseCompanyViewModel;
import com.jiahan.fave.favecomponent.component.company.BaseCompanyViewModelImpl;
import com.jiahan.fave.favecomponent.component.outlet.tracker.OutletTracker;
import com.jiahan.fave.favecomponent.entity.Outlet;

public class OutletSmallItemViewModelImpl extends BaseOutletViewModelImpl implements OutletSmallItemViewModel {
    private final Outlet  mOutlet;
    private final boolean mShowDivider;
    private final boolean mShowAddress;

    public OutletSmallItemViewModelImpl(final Context context,
                                        final OutletTracker outletTracker,
                                        final Outlet outlet,
                                        final boolean showDivider,
                                        final boolean showAddress) {
        super(context, outletTracker, outlet, outlet.mCompany);
        mOutlet = outlet;
        mShowDivider = showDivider;
        mShowAddress = showAddress;
    }

    @Override
    public boolean getDynamicCashbackVisibility() {
        return mOutlet.mHasDynamicCashback;
    }

    @Override
    public String getDynamicCashbackTime() {
        if (!getDynamicCashbackVisibility()) {
            return "";
        }
        return mContext.getString(R.string.now_till,
                DateTimeUtils.formatRFC3339DateTimeForDisplay(mOutlet.mDynamicCashbackInfo.mEndTime,
                        mContext.getString(R.string.display_date_format_time_12)));
    }

    @Override
    public BaseCompanyViewModel getCompanyViewModel() {
        return new BaseCompanyViewModelImpl(mOutlet.mCompany);
    }

    @Override
    public boolean getDividerVisibility() {
        return mShowDivider;
    }

    @Override
    public String getShortAddress() {
        if (mShowAddress) {
            return super.getShortAddress();
        }
        if (mOutlet.mCompany.mTotalOutlets > 1) {
            return mContext.getString(R.string.number_of_location, mOutlet.mCompany.mTotalOutlets);
        }
        return getOutletName();
    }
}