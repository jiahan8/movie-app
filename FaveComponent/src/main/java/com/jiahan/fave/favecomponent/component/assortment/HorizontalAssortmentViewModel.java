package com.jiahan.fave.favecomponent.component.assortment;

import androidx.recyclerview.widget.RecyclerView;

import com.jiahan.fave.core.recyclerView.RecyclerViewAdapter;
import com.jiahan.fave.core.recyclerView.RecyclerViewItemViewModel;

public interface HorizontalAssortmentViewModel extends RecyclerViewItemViewModel {
    RecyclerViewAdapter getAdapter();

    RecyclerView.ItemDecoration getDecoration();

    RecyclerView.LayoutManager getLayoutManager();
}