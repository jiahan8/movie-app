package com.jiahan.fave.movie.feature;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.jiahan.fave.core.common.BaseActivity;
import com.jiahan.fave.favecomponent.interactor.MovieInteractor;
import com.jiahan.fave.movie.di.Injector;

import javax.inject.Inject;
import com.jiahan.fave.movie.R;

public class MovieDetailActivity extends BaseActivity {

    @Inject
    MovieInteractor mConnecter;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_movie_detail;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Injector.inject( this );
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        setUp();
    }

    @Override
    protected void bindViewModel() {
    }

    public void setUp(){
        Bundle bundle = new Bundle();
        bundle.putInt("EXTRA_MOVIE_ID", getIntent().getIntExtra("EXTRA_MOVIE_ID", 0));
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.my_fragment, MovieDetailFragment.newInstance(bundle) )
                .commitAllowingStateLoss();
    }


}
