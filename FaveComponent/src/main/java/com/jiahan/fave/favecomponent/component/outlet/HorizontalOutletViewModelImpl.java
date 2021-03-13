package com.jiahan.fave.favecomponent.component.outlet;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jiahan.fave.core.callback.BaseViewViewModel;
import com.jiahan.fave.core.recyclerView.RecyclerViewAdapter;
import com.jiahan.fave.core.recyclerView.RecyclerViewDividerDecoration;
import com.jiahan.fave.core.recyclerView.RecyclerViewItemViewModel;
import com.jiahan.fave.core.tracker.EventSender;
import com.jiahan.fave.favecomponent.R;
import com.jiahan.fave.favecomponent.adapter.HorizontalSeeMoreAdapterDelegate;
import com.jiahan.fave.favecomponent.adapter.OutletLargeAdapterDelegate;
import com.jiahan.fave.favecomponent.component.outlet.tracker.OutletTracker;
import com.jiahan.fave.favecomponent.component.outlet.tracker.OutletTrackerImpl;
import com.jiahan.fave.favecomponent.component.seeMore.HorizontalSeeMoreItemViewModel;
import com.jiahan.fave.favecomponent.component.seeMore.HorizontalSeeMoreItemViewModelImpl;
import com.jiahan.fave.favecomponent.entity.Outlet;
import com.jiahan.fave.favecomponent.interactor.OutletInteractor;

import java.util.List;

public class HorizontalOutletViewModelImpl implements HorizontalOutletViewModel {
    private final Context             mContext;
    private final BaseViewViewModel   mBaseViewViewModel;
    private final LinearLayoutManager mLayoutManager;

    private final ObservableArrayList<RecyclerViewItemViewModel> mViewModels = new ObservableArrayList<>();

    public HorizontalOutletViewModelImpl(final Context context,
                                         final EventSender eventSender,
                                         final String screenIdentifier,
                                         final BaseViewViewModel baseViewViewModel,
                                         final OutletInteractor interactor,
                                         final List<Outlet> outletList,
                                         final String sectionName,
                                         @Nullable final View.OnClickListener onClickListener,
                                         @Nullable final String category,
                                         @Nullable final String product,
                                         @Nullable final String appFilter,
                                         @Nullable final String location,
                                         @Nullable final String appliedSorting,
                                         @Nullable final Boolean appliedFilter) {
        mContext = context;
        mBaseViewViewModel = baseViewViewModel;
        mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        mViewModels.clear();
        for (int i = 0; i < outletList.size(); i++) {
            Outlet outlet = outletList.get(i);
            OutletTracker tracker = new OutletTrackerImpl(
                    outlet,
                    eventSender,
                    screenIdentifier,
                    sectionName,
                    i + 1);
            if (!TextUtils.isEmpty(category) && !TextUtils.isEmpty(product) && !TextUtils.isEmpty(appFilter) && !TextUtils.isEmpty(location) && appliedFilter != null) {
                tracker.setCategory(category, product, appFilter, location, appliedSorting, appliedFilter);
            }
            OutletLargeViewModel viewModel = new OutletLargeViewModelImpl(context, interactor, tracker, outlet);
            mViewModels.add(viewModel);
        }
        if (onClickListener != null) {
            HorizontalSeeMoreItemViewModel viewModel = new HorizontalSeeMoreItemViewModelImpl(onClickListener);
            mViewModels.add(viewModel);
        }
    }

    @Override
    public RecyclerViewAdapter getAdapter() {
        final LayoutInflater inflater = LayoutInflater.from(mContext);
        return mBaseViewViewModel.createRecyclerViewAdapter(mViewModels,
                new OutletLargeAdapterDelegate(inflater),
                new HorizontalSeeMoreAdapterDelegate(inflater));
    }

    @Override
    public RecyclerView.ItemDecoration getDecoration() {
        return new RecyclerViewDividerDecoration(mContext, DividerItemDecoration.HORIZONTAL, R.drawable.divider_transparent_normal);
    }

    @Override
    public LinearLayoutManager getLayoutManager() {
        return mLayoutManager;
    }
}