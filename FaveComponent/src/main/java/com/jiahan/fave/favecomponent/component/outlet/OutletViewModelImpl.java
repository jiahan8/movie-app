package com.jiahan.fave.favecomponent.component.outlet;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import com.jiahan.fave.core.utils.Constant;
import com.jiahan.fave.core.utils.DateTimeUtils;
import com.jiahan.fave.core.utils.Logger;
import com.jiahan.fave.core.utils.drawableUtils.WidgetDrawable;
import com.jiahan.fave.core.utils.liveDataEventBus.LiveDataEventBus;
import com.jiahan.fave.favecomponent.R;
import com.jiahan.fave.favecomponent.component.company.CompanyViewModel;
import com.jiahan.fave.favecomponent.component.company.CompanyViewModelImpl;
import com.jiahan.fave.favecomponent.component.outlet.tracker.OutletTracker;
import com.jiahan.fave.favecomponent.entity.Outlet;
import com.jiahan.fave.favecomponent.interactor.BaseInteractor;
import com.jiahan.fave.favecomponent.interactor.OutletInteractor;

import autodispose2.AutoDispose;
import autodispose2.androidx.lifecycle.AndroidLifecycleScopeProvider;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import static com.jiahan.fave.core.utils.Constant.REFRESH_MY_FAVE_COMPANY;

public class OutletViewModelImpl extends BaseOutletViewModelImpl implements OutletViewModel {
    private final OutletInteractor mInteractor;
    private final OutletTracker    mOutletTracker;
    private final Outlet           mOutlet;

    protected final ObservableInt mFavouriteIcon = new ObservableInt();

    private boolean mIsFavourite;

    public OutletViewModelImpl(final Context context,
                               final OutletInteractor interactor,
                               final OutletTracker outletTracker,
                               final Outlet outlet) {
        super(context, outletTracker, outlet, outlet.mCompany);
        mInteractor = interactor;
        mOutletTracker = outletTracker;
        mOutlet = outlet;
        mIsFavourite = outlet.mCompany.mFavourite;
        refreshFavouriteIcon();
    }

    @Override
    public CompanyViewModel getCompanyViewModel() {
        return new CompanyViewModelImpl(mOutlet.mCompany);
    }

    @Override
    public ObservableInt getFavouriteIcon() {
        return mFavouriteIcon;
    }

    @Override
    public boolean getFavouriteVisibility() {
        return mInteractor.isUserLogin();
    }

    @Override
    public void onFavouriteClicked(final View view) {
        Observable<?> observable = mIsFavourite ? mInteractor.unFavorited(mOutlet.mCompany.mId, BaseInteractor.FavoriteType.COMPANY.getValue()) :
                mInteractor.favorited(mOutlet.mCompany.mId, BaseInteractor.FavoriteType.COMPANY.getValue());
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .to(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from((LifecycleOwner) mContext, Lifecycle.Event.ON_DESTROY)))
                .subscribe(o -> {
                    mOutletTracker.onTapFavourite();
                    mIsFavourite = !mIsFavourite;
                    refreshFavouriteIcon();
                    LiveDataEventBus.getInstance()
                            .with(Constant.LUCID_REFRESH_ME_TAB_EVENT)
                            .postValue(Constant.LUCID_REFRESH_ME_TAB_EVENT);
                    LiveDataEventBus.getInstance()
                            .with(REFRESH_MY_FAVE_COMPANY, Object.class)
                            .postValue(new Object());
                }, Logger::logException);
    }

    @Override
    public boolean getDynamicCashbackVisibility() {
        return mOutlet.mHasDynamicCashback && mOutlet.mDynamicCashbackInfo != null;
    }

    @Override
    public String getDynamicCashbackTime() {
        if (!getDynamicCashbackVisibility() && !getCashbackRateVisibility()) {
            return "";
        }
        String text = super.getCashbackRate();
        if (getDynamicCashbackVisibility()) {
            text += " " + mContext.getString(R.string.percent_cashback_time_text,
                    DateTimeUtils.formatRFC3339DateTimeForDisplay(mOutlet.mDynamicCashbackInfo.mEndTime,
                            mContext.getString(R.string.display_date_format_time_12)));
        }
        return text;
    }

    @Override
    public Drawable getDynamicCashbackBackground() {
        return WidgetDrawable.getPinkBackground();
    }

    private void refreshFavouriteIcon() {
        mFavouriteIcon.set(mIsFavourite ? R.drawable.ic_heart_filled : R.drawable.ic_heart);
    }
}