package com.jiahan.fave.favecomponent.component.review;

import com.jiahan.fave.core.recyclerView.RecyclerViewItemViewModel;

public interface RatingCategoryItemViewModel extends RecyclerViewItemViewModel {
    String getName();

    int getRatingValue();
}