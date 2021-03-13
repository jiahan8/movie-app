package com.jiahan.fave.favecomponent.component.openingHour;

import android.view.LayoutInflater;

import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jiahan.fave.core.common.BaseDialogFragment;
import com.jiahan.fave.core.common.BaseDialogFragmentViewModelImpl;
import com.jiahan.fave.core.recyclerView.RecyclerViewAdapter;
import com.jiahan.fave.core.recyclerView.RecyclerViewDividerDecoration;
import com.jiahan.fave.core.recyclerView.RecyclerViewItemViewModel;
import com.jiahan.fave.favecomponent.R;
import com.jiahan.fave.favecomponent.adapter.OpenHourListAdapterDelegate;
import com.jiahan.fave.favecomponent.entity.OutletBusinessHour;

import java.util.List;

public class OpeningHourViewModelImpl extends BaseDialogFragmentViewModelImpl implements OpeningHourViewModel {
    private final RecyclerView.LayoutManager mLayoutManager;

    private final ObservableArrayList<RecyclerViewItemViewModel> mViewModels = new ObservableArrayList<>();

    public OpeningHourViewModelImpl(final BaseDialogFragment fragment,
                                    final List<OutletBusinessHour> outletBusinessHourList) {
        super(fragment);
        mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mViewModels.clear();
        for (OutletBusinessHour outletBusinessHour : outletBusinessHourList) {
            OpeningHourItemModel viewModel = new OpeningHourItemModelImpl(mContext, outletBusinessHour);
            mViewModels.add(viewModel);
        }
    }

    @Override
    public RecyclerViewAdapter getAdapter() {
        final LayoutInflater inflater = LayoutInflater.from(mContext);
        return createRecyclerViewAdapter(mViewModels,
                new OpenHourListAdapterDelegate(inflater));
    }

    @Override
    public RecyclerView.ItemDecoration getDecoration() {
        return new RecyclerViewDividerDecoration(mContext, DividerItemDecoration.VERTICAL, R.drawable.divider_vertical);
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return mLayoutManager;
    }
}