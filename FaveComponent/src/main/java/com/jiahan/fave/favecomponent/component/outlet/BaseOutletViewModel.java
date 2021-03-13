package com.jiahan.fave.favecomponent.component.outlet;

import android.view.View;

import com.jiahan.fave.core.recyclerView.RecyclerViewItemViewModel;

public interface BaseOutletViewModel extends RecyclerViewItemViewModel {
    void onItemClicked(View view);

    String getGalleryImage();

    String getOutletName();

    String getFullAddress();

    String getShortAddress();

    int getAddressEllipsizeIndex();

    String getCashbackRate();

    boolean getCashbackRateVisibility();

    boolean getHasFavePay();
}