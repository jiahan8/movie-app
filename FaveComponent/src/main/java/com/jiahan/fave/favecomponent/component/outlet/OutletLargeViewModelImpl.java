package com.jiahan.fave.favecomponent.component.outlet;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

import com.jiahan.fave.core.utils.drawableUtils.DrawableUtils;
import com.jiahan.fave.favecomponent.R;
import com.jiahan.fave.favecomponent.component.outlet.tracker.OutletTracker;
import com.jiahan.fave.favecomponent.entity.Outlet;
import com.jiahan.fave.favecomponent.interactor.OutletInteractor;

public class OutletLargeViewModelImpl extends OutletViewModelImpl implements OutletLargeViewModel {
    public OutletLargeViewModelImpl(final Context context,
                                    final OutletInteractor interactor,
                                    final OutletTracker outletTracker,
                                    final Outlet outlet) {
        super(context, interactor, outletTracker, outlet);
    }

    @Override
    public Drawable getCardBackground() {
        return DrawableUtils.createDrawable(GradientDrawable.RECTANGLE,
                R.dimen.size_16,
                0,
                0,
                R.color.white);
    }
}