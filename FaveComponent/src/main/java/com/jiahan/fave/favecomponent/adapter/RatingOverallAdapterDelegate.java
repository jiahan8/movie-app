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
import com.jiahan.fave.favecomponent.component.review.RatingOverallViewModel;
import com.jiahan.fave.favecomponent.databinding.ViewRatingOverviewBinding;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RatingOverallAdapterDelegate extends ListAdapterDelegate<RatingOverallViewModel, RecyclerViewItemViewModel, RatingOverallAdapterDelegate.ViewHolder> {
    public RatingOverallAdapterDelegate(final LayoutInflater layoutInflater) {
        super(layoutInflater);
    }

    @Override
    protected boolean isForViewType(@NonNull final RecyclerViewItemViewModel item, @NotNull final List<RecyclerViewItemViewModel> items, final int position) {
        return item instanceof RatingOverallViewModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent) {
        final View view = mLayoutInflater.inflate(R.layout.view_rating_overview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull final RatingOverallViewModel viewModel, @NonNull final ViewHolder viewHolder, @NonNull List<Object> payloads) {
        viewHolder.mBinding.setViewModel(viewModel);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ViewRatingOverviewBinding mBinding;

        ViewHolder(final View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
        }
    }
}