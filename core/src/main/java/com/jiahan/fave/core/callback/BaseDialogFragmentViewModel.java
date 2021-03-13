package com.jiahan.fave.core.callback;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;

import com.hannesdorfmann.adapterdelegates4.AdapterDelegate;
import com.jiahan.fave.core.recyclerView.RecyclerViewAdapter;
import com.jiahan.fave.core.recyclerView.RecyclerViewItemViewModel;

public interface BaseDialogFragmentViewModel {
    void onCloseClicked(final View view);

    RecyclerViewAdapter createRecyclerViewAdapter(@NonNull final ObservableArrayList<RecyclerViewItemViewModel> recyclerViewList,
                                                  @NonNull final AdapterDelegate... delegates);
}