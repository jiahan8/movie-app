package com.jiahan.fave.favecomponent.component.deal;

import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.databinding.ObservableInt;

import com.jiahan.fave.favecomponent.component.outlet.OutletViewModel;

public interface DealLargeViewModel extends DealViewModel {
    OutletViewModel getOutletViewModel();

    void onFavouriteClicked(View view);

    ObservableInt getFavouriteIcon();

    String getPurchaseCount();

    Drawable getBoughtBackground();

    boolean getFavouriteVisibility();

    boolean getPurchaseCountVisibility();
}