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
import com.jiahan.fave.favecomponent.component.deal.DealLargeViewModel;
import com.jiahan.fave.favecomponent.databinding.ViewDealCardLargeItemBinding;

import java.util.List;

public class DealLargeAdapterDelegate extends ListAdapterDelegate<DealLargeViewModel, RecyclerViewItemViewModel, DealLargeAdapterDelegate.ViewHolder> {
    public DealLargeAdapterDelegate(final LayoutInflater layoutInflater) {
        super(layoutInflater);
    }

    @Override
    protected boolean isForViewType(@NonNull final RecyclerViewItemViewModel item, @NonNull final List<RecyclerViewItemViewModel> items, final int position) {
        return item instanceof DealLargeViewModel;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.view_deal_card_large_item, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull final DealLargeViewModel viewModel, @NonNull final ViewHolder viewHolder, @NonNull List<Object> payloads) {
        viewHolder.mBinding.setViewModel(viewModel);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ViewDealCardLargeItemBinding mBinding;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
        }
    }

}