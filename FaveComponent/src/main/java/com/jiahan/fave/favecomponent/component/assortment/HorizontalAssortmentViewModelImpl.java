package com.jiahan.fave.favecomponent.component.assortment;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jiahan.fave.core.callback.BaseViewViewModel;
import com.jiahan.fave.core.recyclerView.GridLayoutItemDecoration;
import com.jiahan.fave.core.recyclerView.RecyclerViewAdapter;
import com.jiahan.fave.core.recyclerView.RecyclerViewDividerDecoration;
import com.jiahan.fave.core.recyclerView.RecyclerViewItemViewModel;
import com.jiahan.fave.core.tracker.EventSender;
import com.jiahan.fave.core.tracker.PropertyConstant;
import com.jiahan.fave.core.utils.Constant;
import com.jiahan.fave.core.utils.Utils;
import com.jiahan.fave.favecomponent.R;
import com.jiahan.fave.favecomponent.adapter.AssortmentListAdapterDelegate;
import com.jiahan.fave.favecomponent.component.assortment.tracker.AssortmentTracker;
import com.jiahan.fave.favecomponent.component.assortment.tracker.AssortmentTrackerImpl;
import com.jiahan.fave.favecomponent.entity.Assortment;

import java.util.List;

public class HorizontalAssortmentViewModelImpl implements HorizontalAssortmentViewModel {
    private final Context                     mContext;
    private final BaseViewViewModel           mBaseViewViewModel;
    private final RecyclerView.LayoutManager  mLayoutManager;
    private final RecyclerView.ItemDecoration mItemDecoration;

    private final ObservableArrayList<RecyclerViewItemViewModel> mViewModels = new ObservableArrayList<>();

    public HorizontalAssortmentViewModelImpl(final Context context,
                                             final EventSender eventSender,
                                             final String screenIdentifier,
                                             final BaseViewViewModel baseViewViewModel,
                                             final List<Assortment> assortmentList,
                                             final boolean isGrid,
                                             final String type,
                                             @Nullable final String category,
                                             @Nullable final String product,
                                             @Nullable final String appFilter,
                                             @Nullable final String location,
                                             @Nullable final String appliedSorting,
                                             @Nullable final Boolean appliedFilter) {
        mContext = context;
        mBaseViewViewModel = baseViewViewModel;
        mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        mItemDecoration = new RecyclerViewDividerDecoration(mContext, DividerItemDecoration.HORIZONTAL, R.drawable.divider_transparent_large_extra);
        creatingViewModel(eventSender, screenIdentifier, assortmentList, isGrid, type, category, product, appFilter, location, appliedSorting, appliedFilter);
    }

    public HorizontalAssortmentViewModelImpl(final Context context,
                                             final EventSender eventSender,
                                             final String screenIdentifier,
                                             final BaseViewViewModel baseViewViewModel,
                                             final List<Assortment> assortmentList,
                                             final boolean isGrid,
                                             final String type,
                                             final String location) {
        mContext = context;
        mBaseViewViewModel = baseViewViewModel;
        mLayoutManager = new GridLayoutManager(mContext, Constant.NUMBER_OF_ASSORTMENT_GRID_VIEW_COLUMN, RecyclerView.VERTICAL, false);
        mItemDecoration = new GridLayoutItemDecoration(Constant.NUMBER_OF_ASSORTMENT_GRID_VIEW_COLUMN, R.dimen.margin_large_extra, false);
        creatingViewModel(eventSender, screenIdentifier, assortmentList, isGrid, type, null, null, null, location, null, null);
    }

    @Override
    public RecyclerViewAdapter getAdapter() {
        final LayoutInflater inflater = LayoutInflater.from(mContext);
        return mBaseViewViewModel.createRecyclerViewAdapter(mViewModels,
                new AssortmentListAdapterDelegate(inflater));
    }

    @Override
    public RecyclerView.ItemDecoration getDecoration() {
        return mItemDecoration;
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return mLayoutManager;
    }

    private void creatingViewModel(final EventSender eventSender,
                                   final String screenIdentifier,
                                   final List<Assortment> assortmentList,
                                   final boolean isGrid,
                                   final String type,
                                   @Nullable final String category,
                                   @Nullable final String product,
                                   @Nullable final String appFilter,
                                   @Nullable final String location,
                                   @Nullable final String appliedSorting,
                                   @Nullable final Boolean appliedFilter) {
        int margin = mContext.getResources().getDimensionPixelSize(R.dimen.margin_large_extra);
        int size;
        if (!isGrid) {
            size = mContext.getResources().getDimensionPixelSize(R.dimen.size_160);
        }
        else {
            int column = Constant.NUMBER_OF_ASSORTMENT_GRID_VIEW_COLUMN;
            size = (Utils.getScreenDimension(mContext).widthPixels - (column + 1) * margin) / column;
        }
        mViewModels.clear();
        int position = 0;
        for (Assortment assortment : assortmentList) {
            position++;
            AssortmentTracker tracker = new AssortmentTrackerImpl(
                    assortment,
                    eventSender,
                    screenIdentifier,
                    position,
                    PropertyConstant.Value.SHOW_ASSORTMENT_DETAIL);

            if (!TextUtils.isEmpty(category) && !TextUtils.isEmpty(product) && !TextUtils.isEmpty(appFilter) && !TextUtils.isEmpty(location) && appliedFilter != null) {
                tracker.setCategory(category, product, appFilter, location, appliedSorting, appliedFilter);
            }
            else if (!TextUtils.isEmpty(location)) {
                tracker.setLocation(location);
            }
            AssortmentItemViewModel viewModel = new AssortmentItemViewModelImpl(
                    tracker,
                    assortment,
                    size,
                    type);
            mViewModels.add(viewModel);
        }
    }
}