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
import com.jiahan.fave.favecomponent.component.review.RatingCategoryItemViewModel;
import com.jiahan.fave.favecomponent.databinding.ViewRatingCategoryItemBinding;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RatingCategoryListAdapterDelegate extends ListAdapterDelegate<RatingCategoryItemViewModel, RecyclerViewItemViewModel, RatingCategoryListAdapterDelegate.ViewHolder> {
    public RatingCategoryListAdapterDelegate(final LayoutInflater layoutInflater) {
        super(layoutInflater);
    }

    @Override
    protected boolean isForViewType(@NonNull final RecyclerViewItemViewModel item, @NotNull final List<RecyclerViewItemViewModel> items, final int position) {
        return item instanceof RatingCategoryItemViewModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent) {
        final View view = mLayoutInflater.inflate(R.layout.view_rating_category_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull final RatingCategoryItemViewModel viewModel, @NonNull final ViewHolder viewHolder, @NonNull final List<Object> payloads) {
        viewHolder.mBinding.setViewModel(viewModel);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ViewRatingCategoryItemBinding mBinding;

        ViewHolder(final View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
        }
    }
}