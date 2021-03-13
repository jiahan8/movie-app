package com.jiahan.fave.favecomponent.component.location;

import android.view.View;

import com.jiahan.fave.core.recyclerView.RecyclerViewItemViewModel;

public interface CityItemViewModel extends RecyclerViewItemViewModel {
    String getName();

    boolean getSelected();

    void onItemClicked(final View view);
}