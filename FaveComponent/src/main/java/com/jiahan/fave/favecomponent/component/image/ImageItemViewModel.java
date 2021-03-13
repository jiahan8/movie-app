package com.jiahan.fave.favecomponent.component.image;

import android.view.View;

import com.jiahan.fave.core.feature.common.PhotoGalleriesItemViewModel;

public interface ImageItemViewModel extends PhotoGalleriesItemViewModel {
    void onItemClicked(View view);

    int getWidth();

    int getHeight();

    int getCornerRadius();
}