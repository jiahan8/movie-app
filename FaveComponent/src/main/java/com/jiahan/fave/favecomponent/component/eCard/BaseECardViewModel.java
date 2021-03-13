package com.jiahan.fave.favecomponent.component.eCard;

import android.graphics.drawable.Drawable;
import android.view.View;

import com.jiahan.fave.core.recyclerView.RecyclerViewItemViewModel;

public interface BaseECardViewModel extends RecyclerViewItemViewModel {
    void onItemClicked(View view);

    int getWidth();

    Drawable getCardBackground();

    String getLogo();

    String getCompanyName();

    String getBonus();

    Drawable getBonusBackground();

    boolean getBonusVisibility();

    String getBoughtCount();

    boolean getSoldCountVisibility();

    String getSellingPrice();

    String getValue();

    Drawable getDividerBackground();

    int getTextColor();

    int getLabelColor();
}