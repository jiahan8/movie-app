package com.jiahan.fave.favecomponent.component.deal;

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
import com.jiahan.fave.favecomponent.adapter.DealLargeAdapterDelegate;
import com.jiahan.fave.favecomponent.adapter.HorizontalSeeMoreAdapterDelegate;
import com.jiahan.fave.favecomponent.component.deal.tracker.DealTracker;
import com.jiahan.fave.favecomponent.component.deal.tracker.DealTrackerImpl;
import com.jiahan.fave.favecomponent.component.seeMore.HorizontalSeeMoreItemViewModel;
import com.jiahan.fave.favecomponent.component.seeMore.HorizontalSeeMoreItemViewModelImpl;
import com.jiahan.fave.favecomponent.entity.Deal;
import com.jiahan.fave.favecomponent.interactor.DealInteractor;

import java.util.List;

public class HorizontalDealViewModelImpl implements HorizontalDealViewModel {
    private final Context             mContext;
    private final BaseViewViewModel   mBaseViewViewModel;
    private final LinearLayoutManager mLayoutManager;

    private final ObservableArrayList<RecyclerViewItemViewModel> mViewModels = new ObservableArrayList<>();

    public HorizontalDealViewModelImpl(final Context context,
                                       final EventSender eventSender,
                                       final String screenIdentifier,
                                       final BaseViewViewModel baseViewViewModel,
                                       final DealInteractor interactor,
                                       final List<Deal> dealList,
                                       final String sectionName,
                                       final View.OnClickListener onClickListener) {
        this(context, eventSender, screenIdentifier, baseViewViewModel, interactor, dealList, sectionName, onClickListener, null, null, null, null, null, null);
    }

    public HorizontalDealViewModelImpl(final Context context,
                                       final EventSender eventSender,
                                       final String screenIdentifier,
                                       final BaseViewViewModel baseViewViewModel,
                                       final DealInteractor interactor,
                                       final List<Deal> dealList,
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
        for (int i = 0; i < dealList.size(); i++) {
            Deal dealItem = dealList.get(i);
            DealTracker tracker = new DealTrackerImpl(
                    dealItem,
                    eventSender,
                    screenIdentifier,
                    sectionName,
                    i + 1);
            if (!TextUtils.isEmpty(category) && !TextUtils.isEmpty(product) && !TextUtils.isEmpty(appFilter) && !TextUtils.isEmpty(location) && appliedFilter != null) {
                tracker.setCategory(category, product, appFilter, location, appliedSorting, appliedFilter);
            }
            DealLargeViewModel viewModel = new DealLargeViewModelImpl(context, interactor, dealItem, tracker);
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
                new DealLargeAdapterDelegate(inflater),
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