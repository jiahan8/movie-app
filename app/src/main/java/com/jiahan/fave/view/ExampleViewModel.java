package com.jiahan.fave.view;

import android.view.View;

import com.jiahan.fave.core.utils.AppRoute;
import com.jiahan.fave.core.utils.Constant;

public class ExampleViewModel {

    public ExampleViewModel() {

    }

    public void onMainClicked(final View view) {
        AppRoute.Launcher.Normal(view.getContext(), AppRoute.Movie.getMovieListActivityIntent(view.getContext(), Constant.MOVIE_PRIMARY_DATE_LTE, Constant.MOVIE_PRIMARY_DATE_GTE, Constant.MOVIE_SORT_BY));
    }

}
