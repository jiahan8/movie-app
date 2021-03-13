package com.jiahan.fave.movie.feature;

import android.content.Context;
import android.view.LayoutInflater;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.jiahan.fave.core.common.BaseViewViewModelImpl;
import com.jiahan.fave.core.recyclerView.RecyclerViewAdapter;
import com.jiahan.fave.core.recyclerView.RecyclerViewItemViewModel;
import com.jiahan.fave.core.tracker.EventSender;
import com.jiahan.fave.core.utils.EndlessRecyclerViewScrollListener;
import com.jiahan.fave.favecomponent.adapter.MovieAdapterDelegate;
import com.jiahan.fave.favecomponent.interactor.MovieInteractor;
import com.jiahan.fave.viewmodelanno.ViewModelFactory;

public class MovieDetailViewModelImpl extends BaseViewViewModelImpl implements MovieDetailViewModel {
    private final MovieInteractor    mInteractor;
    private final LinearLayoutManager mLayoutManager;


    private final ObservableArrayList<RecyclerViewItemViewModel> mViewModelList = new ObservableArrayList<>();
    private final ObservableBoolean                              mRefreshing    = new ObservableBoolean();
    private final ObservableBoolean                              mLoading       = new ObservableBoolean();

    @ViewModelFactory
    public MovieDetailViewModelImpl(final Context context,
                                    final String screenIdentifier,
                                    final EventSender eventSender,
                                    final MovieInteractor interactor
    ) {
        super(context, screenIdentifier, eventSender);
        mInteractor = interactor;
        mLayoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);


    }

    @Override
    public RecyclerViewAdapter getAdapter() {
        final LayoutInflater inflater = LayoutInflater.from(mContextReference.get());
        return createRecyclerViewAdapter(mViewModelList, new MovieAdapterDelegate(inflater));
    }

    @Override
    public EndlessRecyclerViewScrollListener getEndLessScrollListener() {
        return null;
    }

    @Override
    public LinearLayoutManager getLayoutManager() {
        return null;
    }

    @Override
    public void setRecyclerView(RecyclerView recyclerView) {

    }

    @Override
    public void loadPage(int page) {

    }

    @Override
    public SwipeRefreshLayout.OnRefreshListener getOnRefreshListener() {
        return null;
    }

    @Override
    public ObservableBoolean getRefreshing() {
        return null;
    }
}
