package com.jiahan.fave.movie.feature;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.jiahan.fave.core.common.BaseViewViewModelImpl;
import com.jiahan.fave.core.network.NetworkUtils;
import com.jiahan.fave.core.recyclerView.LoadingViewModel;
import com.jiahan.fave.core.recyclerView.LoadingViewModelImpl;
import com.jiahan.fave.core.recyclerView.RecyclerViewAdapter;
import com.jiahan.fave.core.recyclerView.RecyclerViewItemViewModel;
import com.jiahan.fave.core.tracker.EventSender;
import com.jiahan.fave.core.utils.EndlessRecyclerViewScrollListener;
import com.jiahan.fave.core.utils.Logger;
import com.jiahan.fave.favecomponent.adapter.MovieAdapterDelegate;
import com.jiahan.fave.favecomponent.component.movie.MovieItemViewModelImpl;
import com.jiahan.fave.favecomponent.entity.Movie;
import com.jiahan.fave.favecomponent.interactor.MovieInteractor;
import com.jiahan.fave.favecomponent.network.pojo.response.MoviesResponse;
import com.jiahan.fave.viewmodelanno.ViewModelFactory;

import java.lang.ref.WeakReference;

import autodispose2.AutoDispose;
import autodispose2.androidx.lifecycle.AndroidLifecycleScopeProvider;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MovieListViewModelImpl extends BaseViewViewModelImpl implements MovieListViewModel {
    private final MovieInteractor    mInteractor;
    private final LinearLayoutManager mLayoutManager;
    private final String mPrimaryReleaseDataLte;
    private final String mPrimaryReleaseDataGte;
    private final String mSortBy;

    private final ObservableArrayList<RecyclerViewItemViewModel> mViewModelList = new ObservableArrayList<>();
    private final ObservableBoolean                              mRefreshing    = new ObservableBoolean();
    private final ObservableBoolean                              mLoading       = new ObservableBoolean();

    @ViewModelFactory
    public MovieListViewModelImpl(final Context context,
                                  final String screenIdentifier,
                                  final EventSender eventSender,
                                  final MovieInteractor interactor,
                                  final String primaryReleaseDataLte,
                                  final String primaryReleaseDataGte,
                                  final String sortBy
    ) {
        super(context, screenIdentifier, eventSender);
        mInteractor = interactor;
        mPrimaryReleaseDataLte = primaryReleaseDataLte;
        mPrimaryReleaseDataGte = primaryReleaseDataGte;
        mSortBy = sortBy;
        mLayoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        if( !NetworkUtils.isNetworkConnected() ) {
            onNoInternetError();
            return;
        }
        loadPage(1);
    }

    @Override
    public void onRetryClicked(View view) {
        loadPage(1);
    }

    @Override
    public RecyclerViewAdapter getAdapter() {
        final LayoutInflater inflater = LayoutInflater.from(mContextReference.get());
        return createRecyclerViewAdapter(mViewModelList, new MovieAdapterDelegate(inflater));
    }

    @Override
    public EndlessRecyclerViewScrollListener getEndLessScrollListener() {
        return new EndlessRecyclerViewScrollListener(mLayoutManager, mLoading, mViewModelList) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                mRecyclerViewReference.get().post( () -> loadPage(page) );
            }
        };
    }

    @Override
    public LinearLayoutManager getLayoutManager() {
        return mLayoutManager;
    }

    @Override
    public void setRecyclerView(RecyclerView recyclerView) {
        mRecyclerViewReference = new WeakReference<>(recyclerView);
    }

    @Override
    public void loadPage(int page) {
        mLoading.set(true);
        if(mViewModelList.isEmpty()) {
            onLoading();
        }
        if( page == 1 ) {
            mViewModelList.clear();
        }
        LoadingViewModel viewModel = new LoadingViewModelImpl();
        mInteractor.getMovie( mPrimaryReleaseDataLte, mPrimaryReleaseDataGte, mSortBy, page )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(
                        disposable -> mViewModelList.add(viewModel)
                )
                .doOnComplete(() -> {
                    mViewModelList.remove(viewModel);
                    mRefreshing.set(false);
                    mLoading.set(false);
                })
                .to(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from((LifecycleOwner) mContextReference.get(), Lifecycle.Event.ON_DESTROY)))
                .subscribe(outletsResponse -> {
                    populate(outletsResponse);
                    onContent();
                }, throwable -> {
                    Logger.logException(throwable);
                });

    }

    @Override
    public SwipeRefreshLayout.OnRefreshListener getOnRefreshListener() {
        return () -> {
            mRefreshing.set(true);
            loadPage(1);
        };
    }

    @Override
    public ObservableBoolean getRefreshing() {
        return mRefreshing;
    }

    private void populate( final MoviesResponse moviesResponse ){
        for (Movie movie : moviesResponse.movies) {
            mViewModelList.add(new MovieItemViewModelImpl(movie));
        }
    }

}
