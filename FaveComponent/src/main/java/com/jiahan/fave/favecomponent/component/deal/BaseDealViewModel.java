package com.jiahan.fave.favecomponent.component.deal;

import android.graphics.drawable.Drawable;
import android.text.Spanned;
import android.view.View;

import com.jiahan.fave.core.recyclerView.RecyclerViewItemViewModel;

public interface BaseDealViewModel extends RecyclerViewItemViewModel {
    void onItemClicked(View view);

    String getDiscount();

    boolean getDiscountVisibility();

    Drawable getDiscountBackground();

    String getDealName();

    Spanned getOriginalPrice();

    String getPrice();

    String getLogo();

    boolean getOriginalPriceVisibility();
}