package com.jiahan.fave.view;

import android.view.View;

import com.jiahan.fave.core.utils.AppRoute;

public class ExampleViewModel {

    public ExampleViewModel() {

    }

    public void onMainClicked(final View view) {
        AppRoute.Launcher.Normal(view.getContext(), AppRoute.Movie.getMovieListActivityIntent(view.getContext()));
    }

}
