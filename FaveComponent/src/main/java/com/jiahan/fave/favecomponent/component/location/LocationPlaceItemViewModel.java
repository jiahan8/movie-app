package com.jiahan.fave.favecomponent.component.location;

import android.view.View;

import com.jiahan.fave.core.recyclerView.RecyclerViewItemViewModel;

public interface LocationPlaceItemViewModel extends RecyclerViewItemViewModel {
    String getPlace();

    void onItemClicked(final View view);
}