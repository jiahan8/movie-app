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
import com.jiahan.fave.favecomponent.component.eCard.BaseECardViewModel;
import com.jiahan.fave.favecomponent.databinding.ViewEcardCardNormalBinding;

import java.util.List;

public class ECardAdapterDelegate extends ListAdapterDelegate<BaseECardViewModel, RecyclerViewItemViewModel, ECardAdapterDelegate.ViewHolder> {
    public ECardAdapterDelegate(final LayoutInflater layoutInflater) {
        super(layoutInflater);
    }

    @Override
    protected boolean isForViewType(@NonNull final RecyclerViewItemViewModel item, @NonNull final List<RecyclerViewItemViewModel> items, final int position) {
        return item instanceof BaseECardViewModel;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.view_ecard_card_normal, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull final BaseECardViewModel item, @NonNull final ViewHolder holder, @NonNull final List<Object> payloads) {
        holder.mBinding.setViewModel(item);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ViewEcardCardNormalBinding mBinding;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
        }
    }
}