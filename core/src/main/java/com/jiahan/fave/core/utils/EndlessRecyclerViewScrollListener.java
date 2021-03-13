package com.jiahan.fave.core.utils;

import androidx.databinding.ObservableBoolean;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class EndlessRecyclerViewScrollListener extends RecyclerView.OnScrollListener {
    // The minimum amount of mItems to have below your current scroll position
    // before loading more.
    private static final int VISIBLE_THRESHOLD = 3;

    // Sets the starting page index
    private static final int STARTING_PAGE_INDEX = 1;

    // The current offset index of data you have loaded
    private int currentPage = 1;

    // The total number of items in the dataset after the last load
    private int previousTotalItemCount = 0;

    // True if we are still waiting for the last set of data to load.
    private final ObservableBoolean loading;

    // True if has more data
    private boolean mHasMore = true;

    private final LinearLayoutManager mLinearLayoutManager;

    // List data, use to get count
    private final List<?> mItems;

    public EndlessRecyclerViewScrollListener(LinearLayoutManager layoutManager,
                                             ObservableBoolean loading,
                                             List<?> items) {
        this.loading = loading;
        this.mLinearLayoutManager = layoutManager;
        this.mItems = items;
    }

    @Override
    public void onScrolled(@NotNull RecyclerView view, int dx, int dy) {
        int lastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();
        int totalItemCount = mItems.size();

        if (totalItemCount <= 1) {
            currentPage = STARTING_PAGE_INDEX;
            mHasMore = true;
        }
        if(loading.get()) {
            return;
        }

        // If the total item count is zero and the previous isn't, assume the
        // list is invalidated and should be reset back to initial state
        if (totalItemCount > 1 && totalItemCount == previousTotalItemCount - 1) {
            mHasMore = false;
        }

        previousTotalItemCount = totalItemCount;

        // If it isn’t currently loading, we check to see if we have breached
        // the visibleThreshold and need to reload more data.
        // If we do need to reload some more data, we execute onLoadMore to fetch the data.
        if (totalItemCount > 1
                && mHasMore
                && totalItemCount <= (lastVisibleItem + VISIBLE_THRESHOLD)
                && !loading.get()
                && ((currentPage == 1 && totalItemCount >= com.jiahan.fave.core.utils.Constant.PAGINATION_LIMIT) || dy > 0)) {
            currentPage++;
            onLoadMore(currentPage, totalItemCount);
        }
    }

    // Defines the process for actually loading more data based on page
    public abstract void onLoadMore(int page, int totalItemsCount);
}