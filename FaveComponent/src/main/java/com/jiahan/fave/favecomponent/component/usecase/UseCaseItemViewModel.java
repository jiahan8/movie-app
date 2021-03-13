package com.jiahan.fave.favecomponent.component.usecase;

import android.view.View;

import com.jiahan.fave.core.recyclerView.RecyclerViewItemViewModel;

public interface UseCaseItemViewModel extends RecyclerViewItemViewModel {
    String getName();

    String getIcon();

    void onItemClicked(View view);
}