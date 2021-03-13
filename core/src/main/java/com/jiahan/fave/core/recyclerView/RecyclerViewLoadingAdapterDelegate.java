package com.jiahan.fave.core.recyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.jiahan.fave.core.R;
import com.jiahan.fave.core.databinding.ViewRecyclerViewLoadingBinding;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RecyclerViewLoadingAdapterDelegate extends com.jiahan.fave.core.recyclerView.ListAdapterDelegate<com.jiahan.fave.core.recyclerView.LoadingViewModel, com.jiahan.fave.core.recyclerView.RecyclerViewItemViewModel, RecyclerViewLoadingAdapterDelegate.ItemViewHolder> {
    public RecyclerViewLoadingAdapterDelegate(final LayoutInflater layoutInflater) {
        super(layoutInflater);
    }

    @Override
    protected boolean isForViewType(@NonNull final com.jiahan.fave.core.recyclerView.RecyclerViewItemViewModel item, @NotNull final List<com.jiahan.fave.core.recyclerView.RecyclerViewItemViewModel> items, final int position) {
        return item instanceof com.jiahan.fave.core.recyclerView.LoadingViewModel;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull final ViewGroup parent) {
        final View view = mLayoutInflater.inflate(R.layout.view_recycler_view_loading, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull final com.jiahan.fave.core.recyclerView.LoadingViewModel item, @NonNull final ItemViewHolder holder, @NonNull final List<Object> payloads) {
        final ViewRecyclerViewLoadingBinding binding = holder.mBinding;
        binding.setViewModel(item);
        binding.executePendingBindings();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        ViewRecyclerViewLoadingBinding mBinding;

        ItemViewHolder(final View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
        }
    }
}