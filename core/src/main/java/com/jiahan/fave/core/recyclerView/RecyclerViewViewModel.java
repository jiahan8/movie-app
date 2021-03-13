package com.jiahan.fave.core.recyclerView;

import androidx.databinding.ObservableBoolean;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public interface RecyclerViewViewModel {
    void setRecyclerView(final RecyclerView recyclerView);

    void loadPage(final int page);

    SwipeRefreshLayout.OnRefreshListener getOnRefreshListener();

    ObservableBoolean getRefreshing();
}