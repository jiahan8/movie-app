package com.jiahan.fave.favecomponent.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.jiahan.fave.core.recyclerView.ListAdapterDelegate;
import com.jiahan.fave.core.recyclerView.RecyclerViewItemViewModel;
import com.jiahan.fave.favecomponent.R;
import com.jiahan.fave.favecomponent.component.image.HorizontalImageViewModel;
import com.jiahan.fave.favecomponent.databinding.ViewHorizontalImageBinding;

import java.util.List;

public class HorizontalImageAdapterDelegate extends ListAdapterDelegate<HorizontalImageViewModel, RecyclerViewItemViewModel, HorizontalImageAdapterDelegate.ViewHolder> {
    public HorizontalImageAdapterDelegate(final LayoutInflater layoutInflater) {
        super(layoutInflater);
    }

    @Override
    protected boolean isForViewType(@NonNull final RecyclerViewItemViewModel item, @NonNull final List<RecyclerViewItemViewModel> items, final int position) {
        return item instanceof HorizontalImageViewModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.view_horizontal_image, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull final HorizontalImageViewModel viewModel, @NonNull final ViewHolder viewHolder, @NonNull List<Object> payloads) {
        viewHolder.mBinding.setViewModel(viewModel);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ViewHorizontalImageBinding mBinding;

        ViewHolder(final View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
        }
    }
}