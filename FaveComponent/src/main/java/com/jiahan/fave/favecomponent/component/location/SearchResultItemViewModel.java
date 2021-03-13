package com.jiahan.fave.favecomponent.component.location;

import android.view.View;

import com.jiahan.fave.core.recyclerView.RecyclerViewItemViewModel;

public interface SearchResultItemViewModel extends RecyclerViewItemViewModel {
    int getIconResource();

    String getName();

    String getAddress();

    void onItemClicked(final View view);
}