package com.jiahan.fave.core.recyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Space;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewSpaceAdapterDelegate extends com.jiahan.fave.core.recyclerView.ListAdapterDelegate<SpaceItemViewModel, com.jiahan.fave.core.recyclerView.RecyclerViewItemViewModel, RecyclerViewSpaceAdapterDelegate.ViewHolder> {
    Context mContext;

    public RecyclerViewSpaceAdapterDelegate(Context context) {
        super(LayoutInflater.from(context));
        mContext = context;
    }

    @Override
    protected boolean isForViewType(@NonNull final com.jiahan.fave.core.recyclerView.RecyclerViewItemViewModel item, @NonNull final List<com.jiahan.fave.core.recyclerView.RecyclerViewItemViewModel> items, final int position) {
        return item instanceof SpaceItemViewModel;
    }

    @NonNull
    @Override
    protected com.jiahan.fave.core.recyclerView.RecyclerViewSpaceAdapterDelegate.ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent) {
        Space space = new Space(parent.getContext());
        return new ViewHolder(space);
    }

    @Override
    protected void onBindViewHolder(@NonNull final SpaceItemViewModel item, @NonNull final com.jiahan.fave.core.recyclerView.RecyclerViewSpaceAdapterDelegate.ViewHolder holder, @NonNull final List<Object> payloads) {
        holder.mSpace.setMinimumWidth(mContext.getResources().getDimensionPixelSize(item.mWidth));
        holder.mSpace.setMinimumHeight(mContext.getResources().getDimensionPixelSize(item.mHeight));
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        Space mSpace;

        ViewHolder(final Space space) {
            super(space);
            mSpace = space;
        }
    }
}