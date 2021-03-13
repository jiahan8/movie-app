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
import com.jiahan.fave.favecomponent.component.movie.MovieItemViewModel;
import com.jiahan.fave.favecomponent.databinding.ViewMovieItemBinding;

import java.util.List;

public class MovieAdapterDelegate extends AbsListItemAdapterDelegate<MovieItemViewModel, RecyclerViewItemViewModel, MovieAdapterDelegate.ViewHolder> {
    private final LayoutInflater mLayoutInflater;

    public MovieAdapterDelegate(final LayoutInflater layoutInflater) {
        mLayoutInflater = layoutInflater;
    }

    @Override
    protected boolean isForViewType(@NonNull final RecyclerViewItemViewModel item, @NonNull final List<RecyclerViewItemViewModel> items, final int position) {
        return item instanceof MovieItemViewModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.view_movie_item, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull final MovieItemViewModel viewModel, @NonNull final ViewHolder viewHolder, @NonNull List<Object> payloads) {
        viewHolder.mBinding.setViewModel(viewModel);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ViewMovieItemBinding mBinding;

        ViewHolder(final View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
        }
    }
}