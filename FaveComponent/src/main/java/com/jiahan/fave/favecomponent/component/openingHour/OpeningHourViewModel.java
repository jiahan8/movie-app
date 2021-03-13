package com.jiahan.fave.favecomponent.component.openingHour;

import androidx.recyclerview.widget.RecyclerView;

import com.jiahan.fave.core.callback.BaseDialogFragmentViewModel;
import com.jiahan.fave.core.recyclerView.RecyclerViewAdapter;

public interface OpeningHourViewModel extends BaseDialogFragmentViewModel {
    RecyclerViewAdapter getAdapter();

    RecyclerView.ItemDecoration getDecoration();

    RecyclerView.LayoutManager getLayoutManager();
}