package com.jiahan.fave.favecomponent.component.eCard;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jiahan.fave.core.recyclerView.RecyclerViewAdapter;
import com.jiahan.fave.core.recyclerView.RecyclerViewItemViewModel;

public interface HorizontalECardViewModel extends RecyclerViewItemViewModel {
    RecyclerViewAdapter getAdapter();

    RecyclerView.ItemDecoration getDecoration();

    LinearLayoutManager getLayoutManager();
}