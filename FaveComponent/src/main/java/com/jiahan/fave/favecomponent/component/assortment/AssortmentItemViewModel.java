package com.jiahan.fave.favecomponent.component.assortment;

import android.graphics.drawable.Drawable;
import android.view.View;

import com.jiahan.fave.core.recyclerView.RecyclerViewItemViewModel;

public interface AssortmentItemViewModel extends RecyclerViewItemViewModel {
    int getSize();

    String getName();

    String getRibbonLabel();

    boolean getRibbonVisibility();

    String getFeatureImage();

    void onItemClicked(View view);

    Drawable getRibbonBackground();
}