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
import com.jiahan.fave.favecomponent.component.label.CompanyLabelItemViewModel;
import com.jiahan.fave.favecomponent.databinding.ViewCompanyLabelItemBinding;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CompanyLabelAdapterDelegate extends ListAdapterDelegate<CompanyLabelItemViewModel, RecyclerViewItemViewModel, CompanyLabelAdapterDelegate.ViewHolder> {
    public CompanyLabelAdapterDelegate(LayoutInflater layoutInflater) {
        super(layoutInflater);
    }

    @Override
    protected boolean isForViewType( @NotNull final RecyclerViewItemViewModel item, @NonNull final List<RecyclerViewItemViewModel> items, final int position) {
        return item instanceof CompanyLabelItemViewModel;
    }

    @NotNull
    @Override
    protected ViewHolder onCreateViewHolder( @NotNull final ViewGroup parent) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.view_company_label_item, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NotNull final CompanyLabelItemViewModel item, @NotNull final ViewHolder holder, @NotNull final List<Object> payloads) {
        holder.mBinding.setViewModel(item);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ViewCompanyLabelItemBinding mBinding;

        public ViewHolder( final View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
        }
    }
}