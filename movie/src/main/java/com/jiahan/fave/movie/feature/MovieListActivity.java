package com.jiahan.fave.movie.feature;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import com.jiahan.fave.core.common.BaseActivity;
import com.jiahan.fave.core.tracker.EventSender;
import com.jiahan.fave.core.tracker.ScreenIdentifier;
import com.jiahan.fave.core.utils.AppRoute;
import com.jiahan.fave.favecomponent.interactor.MovieInteractor;
import com.jiahan.fave.movie.R;
import com.jiahan.fave.movie.databinding.ActivityMovieListBinding;
import com.jiahan.fave.movie.di.Injector;

import javax.inject.Inject;

public class MovieListActivity extends BaseActivity {

    @Inject
    EventSender mEventSender;

    @Inject
    MovieInteractor mInteractor;

    private String mPrimaryReleaseDataLte;
    private String mPrimaryReleaseDataGte;
    private String mSortBy;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_movie_list;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Injector.inject( this );
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void bindViewModel() {
        ViewModelProvider provider = new ViewModelProvider( this, MovieListViewModelImplFactory.create(
                this,
                ScreenIdentifier.SCREEN_MOVIE,
                mEventSender,
                mInteractor,
                mPrimaryReleaseDataLte,
                mPrimaryReleaseDataGte,
                mSortBy
        ));
        MovieListViewModel viewModel = provider.get( MovieListViewModelImpl.class );
        initBinding( viewModel );
        viewModel.setRecyclerView( ((ActivityMovieListBinding) mBinding).recyclerview );
    }

    @Override
    protected void getInputData() {
        Intent intent = getIntent();
        mPrimaryReleaseDataLte = intent.getStringExtra(AppRoute.EXTRA_MOVIE_RELEASE_DATE_LTE);
        mPrimaryReleaseDataGte = intent.getStringExtra(AppRoute.EXTRA_MOVIE_RELEASE_DATE_GTE);
        mSortBy = intent.getStringExtra(AppRoute.EXTRA_MOVIE_SORT_BY);
    }
}
