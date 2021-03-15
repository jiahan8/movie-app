package com.jiahan.fave.movie.feature;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.jiahan.fave.core.common.BaseActivity;
import com.jiahan.fave.core.tracker.EventSender;
import com.jiahan.fave.core.tracker.ScreenIdentifier;
import com.jiahan.fave.favecomponent.interactor.MovieInteractor;
import com.jiahan.fave.movie.R;
import com.jiahan.fave.movie.di.Injector;

import javax.inject.Inject;

public class MovieWebViewActivity extends BaseActivity {

    @Inject
    EventSender mEventSender;

    @Inject
    MovieInteractor mInteractor;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_movie_webview;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Injector.inject( this );
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void bindViewModel() {
        ViewModelProvider provider = new ViewModelProvider( this, MovieWebViewViewModelImplFactory.create(
                this,
                ScreenIdentifier.SCREEN_MOVIE,
                mEventSender,
                mInteractor
        ));
        MovieWebViewViewModel viewModel = provider.get( MovieWebViewViewModelImpl.class );
        initBinding( viewModel );

    }



}