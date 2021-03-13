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
import com.jiahan.fave.favecomponent.component.outlet.OutletLargeViewModel;
import com.jiahan.fave.favecomponent.databinding.ViewOutletCardLargeItemBinding;

import java.util.List;

public class OutletLargeAdapterDelegate extends ListAdapterDelegate<OutletLargeViewModel, RecyclerViewItemViewModel, OutletLargeAdapterDelegate.ViewHolder> {
    public OutletLargeAdapterDelegate(final LayoutInflater layoutInflater) {
        super(layoutInflater);
    }

    @Override
    protected boolean isForViewType(@NonNull final RecyclerViewItemViewModel item, @NonNull final List<RecyclerViewItemViewModel> items, final int position) {
        return item instanceof OutletLargeViewModel;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.view_outlet_card_large_item, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull final OutletLargeViewModel item, @NonNull final ViewHolder holder, @NonNull final List<Object> payloads) {
        holder.binding.setViewModel(item);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ViewOutletCardLargeItemBinding binding;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }

}