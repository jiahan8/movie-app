package com.jiahan.fave.favecomponent.component.image;

import android.content.Context;
import android.view.LayoutInflater;

import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jiahan.fave.core.callback.BaseViewViewModel;
import com.jiahan.fave.core.recyclerView.RecyclerViewAdapter;
import com.jiahan.fave.core.recyclerView.RecyclerViewDividerDecoration;
import com.jiahan.fave.core.recyclerView.RecyclerViewItemViewModel;
import com.jiahan.fave.core.tracker.EventSender;
import com.jiahan.fave.core.tracker.PropertyConstant;
import com.jiahan.fave.core.utils.Utils;
import com.jiahan.fave.favecomponent.R;
import com.jiahan.fave.favecomponent.adapter.ImageAdapterDelegate;
import com.jiahan.fave.favecomponent.component.image.tracker.ImageTracker;
import com.jiahan.fave.favecomponent.component.image.tracker.ImageTrackerImpl;
import com.jiahan.fave.favecomponent.entity.Menu;

import java.util.ArrayList;
import java.util.List;

public class HorizontalImageViewModelImpl implements HorizontalImageViewModel {
    private final Context             mContext;
    private final BaseViewViewModel   mBaseViewViewModel;
    private final LinearLayoutManager mLayoutManager;

    private final ObservableArrayList<RecyclerViewItemViewModel> mViewModels = new ObservableArrayList<>();

    public HorizontalImageViewModelImpl(final Context context,
                                        final EventSender eventSender,
                                        final String screenIdentifier,
                                        final BaseViewViewModel baseViewViewModel,
                                        final List<Menu> menuList) {
        mContext = context;
        mBaseViewViewModel = baseViewViewModel;
        mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        mViewModels.clear();
        int sidePadding = context.getResources().getDimensionPixelSize(R.dimen.size_20);
        int itemDecorationSize = context.getResources().getDimensionPixelSize(R.dimen.margin_small);
        int width = Utils.getScreenDimension(context).widthPixels;
        int size = ((width - (2 * sidePadding) - (3 * itemDecorationSize)) * 5 / 6) / 3;
        int index = 0;
        ArrayList<String> imageList = new ArrayList<>();
        for (Menu menu : menuList) {
            imageList.add(menu.mImage);
            ImageTracker imageTracker = new ImageTrackerImpl(eventSender, screenIdentifier, index);
            ImageItemViewModel viewModel = new ImageItemViewModelImpl(
                    context,
                    imageTracker,
                    size,
                    size,
                    context.getResources().getDimensionPixelSize(R.dimen.size_8),
                    PropertyConstant.Value.SHOW_MENU_PHOTO,
                    imageList,
                    index,
                    menu.mImageThumbnail);
            mViewModels.add(viewModel);
            index++;
        }
    }

    @Override
    public RecyclerViewAdapter getAdapter() {
        final LayoutInflater inflater = LayoutInflater.from(mContext);
        return mBaseViewViewModel.createRecyclerViewAdapter(mViewModels,
                new ImageAdapterDelegate(inflater));
    }

    @Override
    public RecyclerView.ItemDecoration getDecoration() {
        return new RecyclerViewDividerDecoration(mContext, DividerItemDecoration.HORIZONTAL, R.drawable.divider_transparent_small);
    }

    @Override
    public LinearLayoutManager getLayoutManager() {
        return mLayoutManager;
    }
}