package com.jiahan.fave.favecomponent.component.deal;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.Spanned;
import android.view.View;

import com.jiahan.fave.core.utils.AppRoute;
import com.jiahan.fave.core.utils.CustomHtmlTagHandler;
import com.jiahan.fave.core.utils.Utils;
import com.jiahan.fave.core.utils.drawableUtils.WidgetDrawable;
import com.jiahan.fave.favecomponent.R;
import com.jiahan.fave.favecomponent.component.deal.tracker.BaseDealTracker;
import com.jiahan.fave.favecomponent.entity.BaseDeal;
import com.jiahan.fave.favecomponent.entity.Deal;

public class BaseDealViewModelImpl implements BaseDealViewModel {
    protected final Context         mContext;
    protected final BaseDeal        mBaseDeal;
    protected final BaseDealTracker mDealTracker;
    protected final Resources       mResources;

    public BaseDealViewModelImpl(final Context context,
                                 final BaseDeal baseDeal,
                                 final BaseDealTracker dealTracker) {
        mContext = context;
        mBaseDeal = baseDeal;
        mDealTracker = dealTracker;
        mResources = mContext.getResources();
    }

    @Override
    public void onItemClicked(View view) {
        Utils.avoidMultipleClicks(view);
        mDealTracker.onTapDeal();
        long outletId = 0;
        if (mBaseDeal instanceof Deal) {
            outletId = ((Deal) mBaseDeal).mOutlet.mId;
        }
        AppRoute.Launcher.Normal(view.getContext(), AppRoute.DEAL.getDealDetailsActivityIntent(view.getContext(), mBaseDeal.mId, outletId));
    }

    @Override
    public String getDealName() {
        return mBaseDeal.mName;
    }

    @Override
    public Drawable getDiscountBackground() {
        return WidgetDrawable.getPinkBackground();
    }

    @Override
    public String getDiscount() {
        return mContext.getString(R.string.percent_off_text, mBaseDeal.mDiscountPercentage);
    }

    @Override
    public boolean getDiscountVisibility() {
        return mBaseDeal.mDiscountPercentage > 0;
    }

    @Override
    public Spanned getOriginalPrice() {
        return Html.fromHtml(
                String.format("<strike>%1$s</strike>", mBaseDeal.mOriginalPrice),
                null,
                new CustomHtmlTagHandler());
    }

    @Override
    public String getPrice() {
        return mBaseDeal.mDiscountedPrice;
    }

    @Override
    public String getLogo() {
        return mBaseDeal.mLogo;
    }

    @Override
    public boolean getOriginalPriceVisibility() {
        return mBaseDeal.mDiscountPercentage > 0;
    }
}