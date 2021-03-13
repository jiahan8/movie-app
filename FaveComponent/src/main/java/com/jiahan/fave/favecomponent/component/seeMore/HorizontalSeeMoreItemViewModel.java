package com.jiahan.fave.favecomponent.component.seeMore;

import android.view.View;

import com.jiahan.fave.core.recyclerView.RecyclerViewItemViewModel;

public interface HorizontalSeeMoreItemViewModel extends RecyclerViewItemViewModel {
    View.OnClickListener getOnClickListener();
}