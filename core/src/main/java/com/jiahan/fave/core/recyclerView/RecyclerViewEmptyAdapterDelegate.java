package com.jiahan.fave.core.recyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.jiahan.fave.core.R;
import com.jiahan.fave.core.databinding.ViewRecyclerViewEmptyBinding;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RecyclerViewEmptyAdapterDelegate extends com.jiahan.fave.core.recyclerView.ListAdapterDelegate<com.jiahan.fave.core.recyclerView.EmptyViewModelImpl, com.jiahan.fave.core.recyclerView.RecyclerViewItemViewModel, RecyclerViewEmptyAdapterDelegate.ItemViewHolder> {
    public RecyclerViewEmptyAdapterDelegate(final LayoutInflater layoutInflater) {
        super(layoutInflater);
    }

    @Override
    protected boolean isForViewType(@NonNull final com.jiahan.fave.core.recyclerView.RecyclerViewItemViewModel item, @NotNull final List<com.jiahan.fave.core.recyclerView.RecyclerViewItemViewModel> items, final int position) {
        return item instanceof com.jiahan.fave.core.recyclerView.EmptyViewModelImpl;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull final ViewGroup parent) {
        return new ItemViewHolder(mLayoutInflater.inflate(R.layout.view_recycler_view_empty, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull final com.jiahan.fave.core.recyclerView.EmptyViewModelImpl item, @NonNull final ItemViewHolder holder, @NonNull final List<Object> payloads) {
        final ViewRecyclerViewEmptyBinding binding = holder.mBinding;
        binding.setViewModel(item);
        binding.executePendingBindings();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        ViewRecyclerViewEmptyBinding mBinding;

        ItemViewHolder(final View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
        }
    }
}