package com.jiahan.fave.favecomponent.component.deal;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.View;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import com.jiahan.fave.core.utils.Logger;
import com.jiahan.fave.core.utils.drawableUtils.DrawableUtils;
import com.jiahan.fave.core.utils.liveDataEventBus.LiveDataEventBus;
import com.jiahan.fave.favecomponent.R;
import com.jiahan.fave.favecomponent.component.deal.tracker.DealTracker;
import com.jiahan.fave.favecomponent.component.outlet.OutletViewModel;
import com.jiahan.fave.favecomponent.component.outlet.OutletViewModelImpl;
import com.jiahan.fave.favecomponent.entity.Deal;
import com.jiahan.fave.favecomponent.interactor.BaseInteractor;
import com.jiahan.fave.favecomponent.interactor.DealInteractor;

import autodispose2.AutoDispose;
import autodispose2.androidx.lifecycle.AndroidLifecycleScopeProvider;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import static com.jiahan.fave.core.utils.Constant.LUCID_REFRESH_ME_TAB_EVENT;
import static com.jiahan.fave.core.utils.Constant.REFRESH_MY_FAVE_DEAL;

public class DealLargeViewModelImpl extends DealViewModelImpl implements DealLargeViewModel {
    private final DealInteractor mInteractor;
    private final Deal           mDeal;
    private final DealTracker    mDealTracker;

    protected final ObservableInt mFavouriteIcon = new ObservableInt();

    private boolean mIsFavourite;

    public DealLargeViewModelImpl(final Context context,
                                  final DealInteractor interactor,
                                  final Deal deal,
                                  final DealTracker dealTracker) {
        super(context, deal, dealTracker);
        mInteractor = interactor;
        mDeal = deal;
        mDealTracker = dealTracker;
        mIsFavourite = deal.mFavorited;
        refreshFavouriteIcon();
    }

    @Override
    public OutletViewModel getOutletViewModel() {
        return new OutletViewModelImpl(mContext, mInteractor, mDealTracker.getOutletTracker(), mDeal.mOutlet);
    }

    @Override
    public void onFavouriteClicked(final View view) {
        if (!mIsFavourite) {
            mDealTracker.onTapFavourite();
        }
        Observable<?> observable = mIsFavourite ? mInteractor.unFavorited(mDeal.mId, BaseInteractor.FavoriteType.DEAL.getValue()) :
                mInteractor.favorited(mDeal.mId, BaseInteractor.FavoriteType.DEAL.getValue());
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .to(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from((LifecycleOwner) mContext, Lifecycle.Event.ON_DESTROY)))
                .subscribe(o -> {
                    mDealTracker.onTapFavourite();
                    mIsFavourite = !mIsFavourite;
                    refreshFavouriteIcon();
                    LiveDataEventBus.getInstance()
                            .with(LUCID_REFRESH_ME_TAB_EVENT)
                            .postValue(LUCID_REFRESH_ME_TAB_EVENT);
                    LiveDataEventBus.getInstance()
                            .with(REFRESH_MY_FAVE_DEAL, Object.class)
                            .postValue(new Object());
                }, Logger::logException);
    }

    @Override
    public ObservableInt getFavouriteIcon() {
        return mFavouriteIcon;
    }

    @Override
    public String getPurchaseCount() {
        return mResources.getString(R.string.bought, mDeal.mPurchaseCount);
    }

    @Override
    public Drawable getBoughtBackground() {
        return DrawableUtils.createDrawable(
                GradientDrawable.RECTANGLE,
                R.dimen.margin_small,
                0,
                0,
                R.color.light_pink);
    }

    @Override
    public boolean getFavouriteVisibility() {
        return mInteractor.isUserLogin();
    }

    @Override
    public boolean getPurchaseCountVisibility() {
        return mDeal.mPurchaseCount != 0;
    }

    private void refreshFavouriteIcon() {
        mFavouriteIcon.set(mIsFavourite ? R.drawable.ic_heart_filled : R.drawable.ic_heart);
    }
}