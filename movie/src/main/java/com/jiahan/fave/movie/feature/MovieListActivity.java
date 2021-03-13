package com.jiahan.fave.movie.feature;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.jiahan.fave.core.common.BaseActivity;

import com.jiahan.fave.favecomponent.interactor.MovieInteractor;
import com.jiahan.fave.movie.R;
import com.jiahan.fave.movie.di.Injector;

import javax.inject.Inject;

public class MovieListActivity extends BaseActivity {

    @Inject
    MovieInteractor mConnecter;

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

    }


}
