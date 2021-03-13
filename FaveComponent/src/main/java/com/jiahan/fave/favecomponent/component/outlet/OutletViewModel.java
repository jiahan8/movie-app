package com.jiahan.fave.favecomponent.component.outlet;

import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.databinding.ObservableInt;

import com.jiahan.fave.favecomponent.component.company.CompanyViewModel;

public interface OutletViewModel extends BaseOutletViewModel {
    CompanyViewModel getCompanyViewModel();

    ObservableInt getFavouriteIcon();

    boolean getFavouriteVisibility();

    void onFavouriteClicked(View view);

    boolean getDynamicCashbackVisibility();

    String getDynamicCashbackTime();

    Drawable getDynamicCashbackBackground();
}