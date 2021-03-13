package com.jiahan.fave.favecomponent.component.assortment;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;

import com.jiahan.fave.core.utils.AppRoute;
import com.jiahan.fave.core.utils.Utils;
import com.jiahan.fave.core.utils.drawableUtils.WidgetDrawable;
import com.jiahan.fave.favecomponent.component.assortment.tracker.AssortmentTracker;
import com.jiahan.fave.favecomponent.entity.Assortment;

public class AssortmentItemViewModelImpl implements AssortmentItemViewModel {
    private final AssortmentTracker mAssortmentTracker;
    private final Assortment        mAssortment;
    private final int               mSize;
    private final String            mType;

    public AssortmentItemViewModelImpl(final AssortmentTracker assortmentTracker,
                                       final Assortment assortment,
                                       final int size,
                                       final String type) {
        mAssortmentTracker = assortmentTracker;
        mAssortment = assortment;
        mSize = size;
        mType = type;
    }

    @Override
    public int getSize() {
        return mSize;
    }

    @Override
    public String getName() {
        return mAssortment.mName;
    }

    @Override
    public String getRibbonLabel() {
        return mAssortment.mRibbonLabel;
    }

    @Override
    public boolean getRibbonVisibility() {
        return !TextUtils.isEmpty(getRibbonLabel());
    }

    @Override
    public String getFeatureImage() {
        return mAssortment.mFeatureImage;
    }

    @Override
    public void onItemClicked(final View view) {
        Utils.avoidMultipleClicks(view);
        mAssortmentTracker.onTapAssortment();
        AppRoute.Launcher.Normal(view.getContext(), AppRoute.ASSORTMENT.getAssortmentDetailActivityIntent(view.getContext(), mAssortment.mId, mType));
    }

    @Override
    public Drawable getRibbonBackground() {
        return WidgetDrawable.getPinkBackground();
    }
}