package com.jiahan.fave.favecomponent.component.filters;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.databinding.ObservableBoolean;

import com.jiahan.fave.core.recyclerView.RecyclerViewItemViewModel;
import com.jiahan.fave.favecomponent.entity.FilterData;

public interface FiltersSingleChoiceItemViewModel extends RecyclerViewItemViewModel {
    Drawable getBackground();

    ColorStateList getTextColor();

    String getName();

    void onItemClicked(View view);

    ObservableBoolean getSelected();

    void updateSelected(boolean selected);

    String getKey();

    FilterData getData();
}