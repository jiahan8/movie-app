package com.jiahan.fave.core.feature.common;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.jiahan.fave.core.callback.BaseViewViewModel;
import com.jiahan.fave.core.widget.PullBackLayout;

public interface PhotoGalleriesViewModel extends BaseViewViewModel {
    PullBackLayout.OnPullListener getPullBackListener();

    ObservableInt getPullBackBackgroundColor();

    int getDefaultIndex();

    PagerAdapter getAdapter();

    ViewPager.OnPageChangeListener getPageChangeListener();

    ObservableField<String> getCurrentPhotoIndex();
}