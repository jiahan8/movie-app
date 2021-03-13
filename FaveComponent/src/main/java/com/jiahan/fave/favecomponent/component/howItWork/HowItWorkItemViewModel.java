package com.jiahan.fave.favecomponent.component.howItWork;

import com.jiahan.fave.core.recyclerView.RecyclerViewItemViewModel;

public interface HowItWorkItemViewModel extends RecyclerViewItemViewModel {
    String getTitle();

    String getDesc();

    String getImageUrl();

    boolean getImageVisibility();
}