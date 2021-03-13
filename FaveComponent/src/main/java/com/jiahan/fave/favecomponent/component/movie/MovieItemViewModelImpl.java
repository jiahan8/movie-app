package com.jiahan.fave.favecomponent.component.movie;

import android.view.View;

import com.jiahan.fave.core.utils.AppRoute;
import com.jiahan.fave.core.utils.Utils;
import com.jiahan.fave.favecomponent.entity.Movie;

public class MovieItemViewModelImpl implements MovieItemViewModel {
    private final Movie mMovie;

    public MovieItemViewModelImpl( final Movie movie ) {
        mMovie = movie;
    }

    @Override
    public void onItemClicked(View view) {
        Utils.avoidMultipleClicks(view);
        AppRoute.Launcher.Normal(view.getContext(), AppRoute.Movie.getMovieDetailActivityIntent(view.getContext(), mMovie.id));
    }

    @Override
    public String getMovieImage() {
        return mMovie.poster_path;
    }

    @Override
    public String getMovieTitle() {
        return mMovie.title;
    }

    @Override
    public float getMoviePopularity() {
        return mMovie.popularity;
    }
}
