package com.jiahan.fave.favecomponent.component.deal;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jiahan.fave.core.recyclerView.RecyclerViewAdapter;
import com.jiahan.fave.core.recyclerView.RecyclerViewItemViewModel;

public interface HorizontalDealViewModel extends RecyclerViewItemViewModel {
    RecyclerViewAdapter getAdapter();

    RecyclerView.ItemDecoration getDecoration();

    LinearLayoutManager getLayoutManager();
}