package com.jiahan.fave.core.recyclerView;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.recyclerview.widget.RecyclerView;

import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter {
    private final AdapterDelegatesManager<List<RecyclerViewItemViewModel>> mManager;
    private final ObservableArrayList<RecyclerViewItemViewModel> mViewModels;

    public RecyclerViewAdapter(final AdapterDelegatesManager<List<RecyclerViewItemViewModel>> manager,
                               final ObservableArrayList<RecyclerViewItemViewModel> viewModels) {
        mManager = manager;
        mViewModels = viewModels;
        final ObservableList.OnListChangedCallback<ObservableList<RecyclerViewItemViewModel>> onListChangedCallback = new ObservableList.OnListChangedCallback<ObservableList<RecyclerViewItemViewModel>>() {
            @Override
            public void onChanged(final ObservableList sender) {
                notifyDataSetChanged();
            }

            @Override
            public void onItemRangeChanged(final ObservableList sender, final int positionStart, final int itemCount) {
                notifyItemRangeChanged(positionStart, itemCount);
            }

            @Override
            public void onItemRangeInserted(final ObservableList sender, final int positionStart, final int itemCount) {
                notifyItemRangeInserted(positionStart, itemCount);
            }

            @Override
            public void onItemRangeMoved(final ObservableList sender, final int fromPosition, final int toPosition, final int itemCount) {
                notifyItemMoved(fromPosition, toPosition);
            }

            @Override
            public void onItemRangeRemoved(final ObservableList sender, final int positionStart, final int itemCount) {
                notifyItemRangeRemoved(positionStart, itemCount);
            }
        };
        mViewModels.addOnListChangedCallback(onListChangedCallback);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        return mManager.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        mManager.onBindViewHolder(mViewModels, position, holder);
    }

    @Override
    public int getItemViewType(final int position) {
        return mManager.getItemViewType(mViewModels, position);
    }

    @Override
    public int getItemCount() {
        return mViewModels.size();
    }
}