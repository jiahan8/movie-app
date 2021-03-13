package com.jiahan.fave.core.feature.common;

import android.view.View;

import com.jiahan.fave.core.recyclerView.RecyclerViewItemViewModel;

public interface PhotoGalleriesItemViewModel extends RecyclerViewItemViewModel {
    String getImageUrl();

    View.OnLongClickListener getOnLongClickedListener();
}