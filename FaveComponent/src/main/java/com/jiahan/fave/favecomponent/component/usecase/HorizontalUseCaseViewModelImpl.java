package com.jiahan.fave.favecomponent.component.usecase;

import android.content.Context;
import android.view.LayoutInflater;

import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jiahan.fave.core.callback.BaseViewViewModel;
import com.jiahan.fave.core.recyclerView.GridLayoutItemDecoration;
import com.jiahan.fave.core.recyclerView.RecyclerViewAdapter;
import com.jiahan.fave.core.recyclerView.RecyclerViewItemViewModel;
import com.jiahan.fave.core.tracker.EventSender;
import com.jiahan.fave.core.utils.Constant;
import com.jiahan.fave.favecomponent.R;
import com.jiahan.fave.favecomponent.adapter.UseCaseListAdapterDelegate;
import com.jiahan.fave.favecomponent.component.usecase.tracker.UseCaseTracker;
import com.jiahan.fave.favecomponent.component.usecase.tracker.UseCaseTrackerImpl;
import com.jiahan.fave.favecomponent.entity.UseCase;

import java.util.List;

public class HorizontalUseCaseViewModelImpl implements HorizontalUseCaseViewModel {
    private final Context           mContext;
    private final BaseViewViewModel mBaseViewViewModel;
    private final GridLayoutManager mLayoutManager;

    private final ObservableArrayList<RecyclerViewItemViewModel> mViewModels = new ObservableArrayList<>();

    public HorizontalUseCaseViewModelImpl(final Context context,
                                          final EventSender eventSender,
                                          final String screenIdentifier,
                                          final BaseViewViewModel baseViewViewModel,
                                          final List<UseCase> useCaseList,
                                          final String sectionName) {
        mContext = context;
        mBaseViewViewModel = baseViewViewModel;
        mLayoutManager = new GridLayoutManager(mContext, Constant.NUMBER_OF_EXPLORE_USE_CASES_VIEW_COLUMN, RecyclerView.VERTICAL, false);
        int position = 0;
        for (UseCase useCase : useCaseList) {
            position++;
            UseCaseTracker tracker = new UseCaseTrackerImpl(
                    eventSender,
                    screenIdentifier,
                    sectionName,
                    position,
                    useCase);
            UseCaseItemViewModel viewModel = new UseCaseItemViewModelImpl(tracker, useCase);
            mViewModels.add(viewModel);
        }
    }

    @Override
    public RecyclerViewAdapter getAdapter() {
        final LayoutInflater inflater = LayoutInflater.from(mContext);
        return mBaseViewViewModel.createRecyclerViewAdapter(mViewModels,
                new UseCaseListAdapterDelegate(inflater));
    }

    @Override
    public RecyclerView.ItemDecoration getDecoration() {
        return new GridLayoutItemDecoration(Constant.NUMBER_OF_EXPLORE_USE_CASES_VIEW_COLUMN, R.dimen.margin_small, false);
    }

    @Override
    public LinearLayoutManager getLayoutManager() {
        return mLayoutManager;
    }
}