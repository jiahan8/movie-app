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
import com.jiahan.fave.favecomponent.component.deal.HorizontalDealViewModel;
import com.jiahan.fave.favecomponent.databinding.ViewHorizontalDealBinding;

import java.util.List;

public class HorizontalDealAdapterDelegate extends ListAdapterDelegate<HorizontalDealViewModel, RecyclerViewItemViewModel, HorizontalDealAdapterDelegate.ViewHolder> {
    public HorizontalDealAdapterDelegate(final LayoutInflater layoutInflater) {
        super(layoutInflater);
    }

    @Override
    protected boolean isForViewType(@NonNull final RecyclerViewItemViewModel item, @NonNull final List<RecyclerViewItemViewModel> items, final int position) {
        return item instanceof HorizontalDealViewModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.view_horizontal_deal, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull final HorizontalDealViewModel viewModel, @NonNull final ViewHolder viewHolder, @NonNull List<Object> payloads) {
        viewHolder.mBinding.setViewModel(viewModel);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ViewHorizontalDealBinding mBinding;

        ViewHolder(final View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
        }
    }
}