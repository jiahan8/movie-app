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
import com.jiahan.fave.favecomponent.component.usecase.HorizontalUseCaseViewModel;
import com.jiahan.fave.favecomponent.databinding.ViewHorizontalUseCaseBinding;

import java.util.List;

public class HorizontalUseCaseAdapterDelegate extends ListAdapterDelegate<HorizontalUseCaseViewModel, RecyclerViewItemViewModel, HorizontalUseCaseAdapterDelegate.ViewHolder> {
    public HorizontalUseCaseAdapterDelegate(final LayoutInflater layoutInflater) {
        super(layoutInflater);
    }

    @Override
    protected boolean isForViewType(@NonNull final RecyclerViewItemViewModel item, @NonNull final List<RecyclerViewItemViewModel> items, final int position) {
        return item instanceof HorizontalUseCaseViewModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.view_horizontal_use_case, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull final HorizontalUseCaseViewModel viewModel, @NonNull final ViewHolder viewHolder, @NonNull List<Object> payloads) {
        viewHolder.mBinding.setViewModel(viewModel);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ViewHorizontalUseCaseBinding mBinding;

        ViewHolder(final View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
        }
    }
}