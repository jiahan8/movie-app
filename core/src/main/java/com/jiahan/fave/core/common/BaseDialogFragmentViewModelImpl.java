package com.jiahan.fave.core.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;

import com.hannesdorfmann.adapterdelegates4.AdapterDelegate;
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager;
import com.jiahan.fave.core.callback.BaseDialogFragmentViewModel;
import com.jiahan.fave.core.recyclerView.RecyclerViewAdapter;
import com.jiahan.fave.core.recyclerView.RecyclerViewEmptyAdapterDelegate;
import com.jiahan.fave.core.recyclerView.RecyclerViewItemViewModel;
import com.jiahan.fave.core.recyclerView.RecyclerViewLabelAdapterDelegate;
import com.jiahan.fave.core.recyclerView.RecyclerViewLoadingAdapterDelegate;
import com.jiahan.fave.core.recyclerView.RecyclerViewSpaceAdapterDelegate;

import java.util.List;

public abstract class BaseDialogFragmentViewModelImpl implements BaseDialogFragmentViewModel {
    protected final BaseDialogFragment mFragment;
    protected final Context            mContext;

    public BaseDialogFragmentViewModelImpl(final BaseDialogFragment fragment) {
        mFragment = fragment;
        mContext = fragment.getContext();
    }

    @Override
    public void onCloseClicked(final View view) {
        mFragment.dismiss();
    }

    @Override
    public RecyclerViewAdapter createRecyclerViewAdapter(@NonNull final ObservableArrayList<RecyclerViewItemViewModel> recyclerViewList,
                                                         @NonNull final AdapterDelegate... delegates) {
        final AdapterDelegatesManager<List<RecyclerViewItemViewModel>> manager = new AdapterDelegatesManager<>();
        if (mContext != null) {
            final LayoutInflater inflater = LayoutInflater.from(mContext);
            manager.addDelegate(new RecyclerViewLoadingAdapterDelegate(inflater));
            manager.addDelegate(new RecyclerViewEmptyAdapterDelegate(inflater));
            manager.addDelegate(new RecyclerViewLabelAdapterDelegate(mContext));
            manager.addDelegate(new RecyclerViewSpaceAdapterDelegate(mContext));
        }
        for (AdapterDelegate adapterDelegate : delegates) {
            manager.addDelegate(adapterDelegate);
        }
        return new RecyclerViewAdapter(manager, recyclerViewList);
    }
}