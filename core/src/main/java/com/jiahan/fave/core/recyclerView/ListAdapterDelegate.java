package com.jiahan.fave.core.recyclerView;

import android.view.LayoutInflater;

import androidx.recyclerview.widget.RecyclerView;

import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate;

public abstract class ListAdapterDelegate<I extends T, T, VH extends RecyclerView.ViewHolder> extends AbsListItemAdapterDelegate<I, T, VH> {
    public final LayoutInflater mLayoutInflater;

    public ListAdapterDelegate(final LayoutInflater layoutInflater) {
        mLayoutInflater = layoutInflater;
    }
}