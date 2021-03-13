package com.jiahan.fave.favecomponent.component.seeMore;

import android.view.View;

import androidx.annotation.Nullable;

public class HorizontalSeeMoreItemViewModelImpl implements HorizontalSeeMoreItemViewModel {
    private final View.OnClickListener mOnClickListener;

    public HorizontalSeeMoreItemViewModelImpl(@Nullable final View.OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    @Override
    public View.OnClickListener getOnClickListener() {
        return mOnClickListener;
    }
}