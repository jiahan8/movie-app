package com.jiahan.fave.core.callback;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.DrawableRes;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import com.google.android.material.appbar.AppBarLayout;

public interface BaseToolbarViewModel {
    AppBarLayout.OnOffsetChangedListener getOnOffsetChangedListener();

    int getCustomToolbarLayout();

    ObservableField<String> getToolbarTitle();

    ObservableInt getNavigationIcon();

    ObservableBoolean getToolbarTitleVisibility();

    void onOptionsItemSelected(MenuItem item);

    void setToolbarTitle(String title);

    void setNavigationIcon(@DrawableRes final int iconResource);

    void onBackClicked(View view);
}