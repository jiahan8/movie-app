package com.jiahan.fave.favecomponent.component.outlet;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.jiahan.fave.core.common.BaseActivity;
import com.jiahan.fave.core.utils.AppRoute;
import com.jiahan.fave.core.utils.DialogFragmentUtil;
import com.jiahan.fave.core.utils.drawableUtils.WidgetDrawable;
import com.jiahan.fave.favecomponent.R;
import com.jiahan.fave.favecomponent.component.company.CompanyViewModel;
import com.jiahan.fave.favecomponent.component.company.CompanyViewModelImpl;
import com.jiahan.fave.favecomponent.component.openingHour.OpeningHourFragment;
import com.jiahan.fave.favecomponent.component.outlet.tracker.OutletTracker;
import com.jiahan.fave.favecomponent.entity.Outlet;
import com.jiahan.fave.favecomponent.interactor.OutletInteractor;

import java.util.ArrayList;

public class OutletXLargeViewModelImpl extends OutletViewModelImpl implements OutletXLargeViewModel {
    private final Context       mContext;
    private final OutletTracker mOutletTracker;
    private final Outlet        mOutlet;

    public OutletXLargeViewModelImpl(final Context context,
                                     final OutletInteractor interactor,
                                     final OutletTracker tracker,
                                     final Outlet outlet) {
        super(context, interactor, tracker, outlet);
        mContext = context;
        mOutletTracker = tracker;
        mOutlet = outlet;
    }

    @Override
    public CompanyViewModel getCompanyViewModel() {
        return new CompanyViewModelImpl(mOutlet.mCompany);
    }

    @Override
    public String getOutletOpenHour() {
        if (mOutlet.mOutletTiming != null && mOutlet.mOutletTiming.openNow)
            return mContext.getString(R.string.outlet_open_now);
        return null;
    }

    @Override
    public String getOutletCloseHour() {
        if (mOutlet.mOutletTiming == null) return null;
        if (mOutlet.mOutletTiming.openNow)
            return mContext.getString(R.string.outlet_next_close_hour, mOutlet.mOutletTiming.nextClosingHour);
        else
            return mContext.getString(R.string.outlet_next_open_hour, mOutlet.mOutletTiming.nextOpenHour);
    }

    @Override
    public boolean getOutletHourAvailable() {
        return mOutlet.mOutletTiming != null &&
                mOutlet.mOutletTiming.outletBusinessHours != null &&
                mOutlet.mOutletTiming.outletBusinessHours.size() > 0;
    }

    @Override
    public void onBusinessHourClicked(final View view) {
        if (mOutlet.mOutletTiming == null || mOutlet.mOutletTiming.outletBusinessHours.isEmpty()) {
            return;
        }
        mOutletTracker.onTapOpeningHours();
        DialogFragmentUtil.displayFragment(
                (BaseActivity) mContext,
                OpeningHourFragment.newInstance(new ArrayList<>(mOutlet.mOutletTiming.outletBusinessHours)),
                OpeningHourFragment.TAG);
    }

    @Override
    public Drawable getPayBackground() {
        return WidgetDrawable.getDefaultButtonBackground();
    }

    @Override
    public void onPayClicked(final View view) {
        mOutletTracker.onTapPayNow();
        AppRoute.Launcher.Normal(view.getContext(), AppRoute.OLD.getFavePayActivityIntent(view.getContext(), mOutlet.mId, mOutlet.mCompany.mId));
    }

    @Override
    public String getPayCount() {
        return mOutlet.mFavepayCount != 0 ? mContext.getResources().getString(R.string.favepay_paid_here, mOutlet.mFavepayCount) : "";
    }

    @Override
    public boolean getPeopleCountLabelVisibility() {
        return mOutlet.mFavepayCount > 0;
    }
}