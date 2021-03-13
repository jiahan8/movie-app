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
import com.jiahan.fave.favecomponent.component.deal.DealSmallItemViewModel;
import com.jiahan.fave.favecomponent.databinding.ViewDealCardSmallItemBinding;

import java.util.List;

public class DealSmallAdapterDelegate extends ListAdapterDelegate<DealSmallItemViewModel, RecyclerViewItemViewModel, DealSmallAdapterDelegate.ViewHolder> {
    public DealSmallAdapterDelegate(final LayoutInflater layoutInflater) {
        super(layoutInflater);
    }

    @Override
    protected boolean isForViewType(@NonNull final RecyclerViewItemViewModel item, @NonNull final List<RecyclerViewItemViewModel> items, final int position) {
        return item instanceof DealSmallItemViewModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.view_deal_card_small_item, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull final DealSmallItemViewModel viewModel, @NonNull final ViewHolder viewHolder, @NonNull List<Object> payloads) {
        viewHolder.mBinding.setViewModel(viewModel);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ViewDealCardSmallItemBinding mBinding;

        ViewHolder(final View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
        }
    }
}