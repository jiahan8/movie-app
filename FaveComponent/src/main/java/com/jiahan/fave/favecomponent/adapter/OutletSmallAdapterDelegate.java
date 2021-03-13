package com.jiahan.fave.favecomponent.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate;
import com.jiahan.fave.core.recyclerView.RecyclerViewItemViewModel;
import com.jiahan.fave.favecomponent.R;
import com.jiahan.fave.favecomponent.component.outlet.OutletSmallItemViewModel;
import com.jiahan.fave.favecomponent.databinding.ViewOutletCardSmallItemBinding;

import java.util.List;

public class OutletSmallAdapterDelegate extends AbsListItemAdapterDelegate<OutletSmallItemViewModel, RecyclerViewItemViewModel, OutletSmallAdapterDelegate.ViewHolder> {
    private final LayoutInflater mLayoutInflater;

    public OutletSmallAdapterDelegate(final LayoutInflater layoutInflater) {
        mLayoutInflater = layoutInflater;
    }

    @Override
    protected boolean isForViewType(@NonNull final RecyclerViewItemViewModel item, @NonNull final List<RecyclerViewItemViewModel> items, final int position) {
        return item instanceof OutletSmallItemViewModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.view_outlet_card_small_item, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull final OutletSmallItemViewModel viewModel, @NonNull final ViewHolder viewHolder, @NonNull List<Object> payloads) {
        viewHolder.mBinding.setViewModel(viewModel);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ViewOutletCardSmallItemBinding mBinding;

        ViewHolder(final View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
        }
    }
}