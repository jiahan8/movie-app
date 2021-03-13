package com.jiahan.fave.favecomponent.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.jiahan.fave.core.recyclerView.ListAdapterDelegate;
import com.jiahan.fave.core.recyclerView.RecyclerViewItemViewModel;
import com.jiahan.fave.favecomponent.R;
import com.jiahan.fave.favecomponent.component.needHelp.NeedHelpItemViewModel;
import com.jiahan.fave.favecomponent.databinding.ViewNeedHelpItemBinding;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NeedHelpAdapterDelegate
        extends ListAdapterDelegate<NeedHelpItemViewModel, RecyclerViewItemViewModel, NeedHelpAdapterDelegate.ViewHolder> {
    public NeedHelpAdapterDelegate(LayoutInflater layoutInflater) {
        super(layoutInflater);
    }

    @Override
    protected boolean isForViewType(@NotNull RecyclerViewItemViewModel item, @NotNull List<RecyclerViewItemViewModel> items, int position) {
        return item instanceof NeedHelpItemViewModel;
    }


    @NotNull
    @Override
    protected ViewHolder onCreateViewHolder(@NotNull ViewGroup parent) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.view_need_help_item, parent, false));

    }

    @Override
    protected void onBindViewHolder(@NotNull NeedHelpItemViewModel item, @NotNull ViewHolder holder, @NotNull List<Object> payloads) {
        holder.mBinding.setViewModel(item);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ViewNeedHelpItemBinding mBinding;

        public ViewHolder(final View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
        }
    }
}
