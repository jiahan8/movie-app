package com.jiahan.fave.favecomponent.component.eCard;

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
import com.jiahan.fave.core.utils.Utils;
import com.jiahan.fave.favecomponent.R;
import com.jiahan.fave.favecomponent.adapter.ECardAdapterDelegate;
import com.jiahan.fave.favecomponent.adapter.HorizontalSeeMoreAdapterDelegate;
import com.jiahan.fave.favecomponent.component.eCard.tracker.BaseECardTracker;
import com.jiahan.fave.favecomponent.component.eCard.tracker.BaseECardTrackerImpl;
import com.jiahan.fave.favecomponent.component.seeMore.HorizontalSeeMoreItemViewModel;
import com.jiahan.fave.favecomponent.component.seeMore.HorizontalSeeMoreItemViewModelImpl;
import com.jiahan.fave.favecomponent.entity.BaseECard;

import java.util.List;

public class HorizontalECardViewModelImpl implements HorizontalECardViewModel {
    private final Context             mContext;
    private final BaseViewViewModel   mBaseViewViewModel;
    private final LinearLayoutManager mLayoutManager;

    private final ObservableArrayList<RecyclerViewItemViewModel> mViewModels = new ObservableArrayList<>();

    public HorizontalECardViewModelImpl(final Context context,
                                        final EventSender eventSender,
                                        final String screenIdentifier,
                                        final BaseViewViewModel baseViewViewModel,
                                        final List<BaseECard> eCardList,
                                        final String sectionName,
                                        @Nullable final View.OnClickListener onClickListener,
                                        @Nullable final String category,
                                        @Nullable final String appFilter,
                                        @Nullable final String location,
                                        @Nullable final String appliedSorting,
                                        @Nullable final Boolean appliedFilter) {
        mContext = context;
        mBaseViewViewModel = baseViewViewModel;
        mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        mViewModels.clear();
        for (int i = 0; i < eCardList.size(); i++) {
            BaseECard eCard = eCardList.get(i);
            BaseECardTracker tracker = new BaseECardTrackerImpl(
                    eCard,
                    eventSender,
                    screenIdentifier,
                    sectionName,
                    i + 1);
            if (!TextUtils.isEmpty(category) && !TextUtils.isEmpty(appFilter) && !TextUtils.isEmpty(location) && appliedFilter != null) {
                tracker.setCategory(category, appFilter, location, appliedSorting, appliedFilter);
            }
            int width = Utils.getScreenDimension(context).widthPixels * 4 / 5;
            BaseECardViewModel viewModel = new BaseECardViewModelImpl(context, tracker, eCard, eCard.mCompanyId, width);
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
                new ECardAdapterDelegate(inflater),
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