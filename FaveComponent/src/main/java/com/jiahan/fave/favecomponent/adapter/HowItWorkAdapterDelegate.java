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
import com.jiahan.fave.favecomponent.component.howItWork.HowItWorkItemViewModel;
import com.jiahan.fave.favecomponent.databinding.ViewHowItWorkItemBinding;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HowItWorkAdapterDelegate extends ListAdapterDelegate<HowItWorkItemViewModel, RecyclerViewItemViewModel, HowItWorkAdapterDelegate.ViewHolder> {
    public HowItWorkAdapterDelegate(LayoutInflater layoutInflater) {
        super(layoutInflater);
    }

    @Override
    protected boolean isForViewType(@NotNull RecyclerViewItemViewModel item, @NotNull List<RecyclerViewItemViewModel> items, int position) {
        return item instanceof HowItWorkItemViewModel;
    }

    @NotNull
    @Override
    protected ViewHolder onCreateViewHolder(@NotNull ViewGroup parent) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.view_how_it_work_item, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NotNull HowItWorkItemViewModel item, @NotNull ViewHolder holder, @NotNull List<Object> payloads) {
        holder.mBinding.setViewModel(item);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ViewHowItWorkItemBinding mBinding;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
        }
    }
}