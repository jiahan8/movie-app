package com.jiahan.fave.core.recyclerView;

import androidx.annotation.DimenRes;

import com.jiahan.fave.core.R;

public class SpaceItemViewModel implements com.jiahan.fave.core.recyclerView.RecyclerViewItemViewModel {
    public final int mWidth;
    public final int mHeight;

    public SpaceItemViewModel() {
        this(R.dimen.margin_large);
    }

    public SpaceItemViewModel(@DimenRes int size) {
        mWidth = size;
        mHeight = size;
    }
}