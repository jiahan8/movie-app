package com.jiahan.fave.favecomponent.component.banner;

import android.view.View;

import com.jiahan.fave.core.recyclerView.RecyclerViewItemViewModel;

public interface BannerViewModel extends RecyclerViewItemViewModel {
    String getBannerImage();

    void onItemClicked(View view);
}
