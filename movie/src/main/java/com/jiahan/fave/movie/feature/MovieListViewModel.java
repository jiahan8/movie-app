package com.jiahan.fave.movie.feature;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.jiahan.fave.core.callback.BaseViewViewModel;
import com.jiahan.fave.core.recyclerView.RecyclerViewAdapter;
import com.jiahan.fave.core.recyclerView.RecyclerViewViewModel;
import com.jiahan.fave.core.utils.EndlessRecyclerViewScrollListener;

public interface MovieListViewModel extends BaseViewViewModel, RecyclerViewViewModel {
//    ObservableArrayList<RecyclerViewItemViewModel> getItemList();

    RecyclerViewAdapter getAdapter();

    EndlessRecyclerViewScrollListener getEndLessScrollListener();

    LinearLayoutManager getLayoutManager();
}
